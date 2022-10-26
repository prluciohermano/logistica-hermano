package com.garagecontrolsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.garagecontrolsystem.entity.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	@Query(value = "select p from Veiculo p where upper(trim(p.descricao)) like %?1% order by descricao")
	List<Veiculo> findVeiculoByDesc(String descBusca);

	@Query(value = "select obj from Veiculo obj where obj.categoria.id = :id_cat order by descricao")
	List<Veiculo> findAllByCategoria(@Param(value = "id_cat") Long id_cat);

	public List<Veiculo> findByOrderByDescricao();

	@Query(value = "select p from Veiculo p where upper(trim(p.placaCar)) like %?1%")
	List<Veiculo> findByPlaca(String placaCarro);

	
}
