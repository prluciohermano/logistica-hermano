package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.dto.VeiculoDTO;
import com.garagecontrolsystem.entity.Categoria;
import com.garagecontrolsystem.entity.GarageBoxModel;
import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.entity.Veiculo;
import com.garagecontrolsystem.repository.GarageBoxRepository;
import com.garagecontrolsystem.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	VeiculoRepository veiculoRepository;

	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	GarageBoxRepository garageBoxRepository;

	@Transactional
	public Veiculo save(Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}

	@Transactional
	public Veiculo create(Long id_cat, Veiculo obj) {
		obj.setId(null);
		Categoria cat = categoriaService.findById(id_cat);
		obj.setCategoria(cat);
		return veiculoRepository.save(obj);
	}

	public Veiculo findById(Long id) {
		Optional<Veiculo> obj = veiculoRepository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id, Veiculo.class.getName()));
	}

	@Transactional
	public void deleteById(Long id) {
		veiculoRepository.deleteById(id);
	}

	public List<Veiculo> findByOrderByDescricao() {
		return veiculoRepository.findByOrderByDescricao();
	}

	public List<Veiculo> findAllByCategoria(Long id_cat) {
		categoriaService.findById(id_cat);
		return veiculoRepository.findAllByCategoria(id_cat);
	}

	public List<Veiculo> findVeiculoByName(String descBusca) {
		return veiculoRepository.findVeiculoByDesc(descBusca);

	}

	public Veiculo update(Long id, Veiculo obj) {
		Veiculo newObj = findById(id);
		updateData(newObj, obj);
		return veiculoRepository.save(newObj);
	}

	private void updateData(Veiculo newObj, Veiculo obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setAnoModelo(obj.getAnoModelo());
		newObj.setCorVeiculo(obj.getCorVeiculo());
//		newObj.setDataEntrada(obj.getDataEntrada());
		newObj.setPrecoEntrada(obj.getPrecoEntrada());

	}

	public void delete(Long id) {
		findById(id);
		try {
			veiculoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.garagecontrolsystem.service.exceptions.DataIntegrityViolationException(
					"Veiculo não pode ser deletado! Possui arquivos associados.");
		}
	}

	public ResponseEntity<GarageBoxModel> saveByGarageInById(Long boxId, GarageBoxModel garageBox) {

		Optional<Veiculo> veiculo = veiculoRepository.findById(boxId);
			if (veiculo.isPresent()) {
				
				garageBox.setVeiculo(veiculo.get());
				Optional<Veiculo> pessoaModel = veiculoRepository.findById(boxId);
				garageBox.setVeiculo(pessoaModel.get());
				garageBoxRepository.save(garageBox);
				return ResponseEntity.status(HttpStatus.CREATED).body(garageBox);
			}
			
		return ResponseEntity.notFound().build();
	}
	

	public List<GarageBoxModel> findAllByVeiculoId(Long veiculoId) {
		Optional<Veiculo> veiculo = veiculoRepository.findById(veiculoId);
		
		if (veiculo.isPresent()) {
			return garageBoxRepository.findByVeiculo(veiculo.get());
		}
		return null;
	}

	public List<Veiculo> findByPlaca(String placaCarro) {
		
		return veiculoRepository.findByPlaca(placaCarro);
	}

}
