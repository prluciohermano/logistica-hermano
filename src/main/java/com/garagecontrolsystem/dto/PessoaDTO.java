package com.garagecontrolsystem.dto;

import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.entity.TipoPessoaModel;
import com.garagecontrolsystem.entity.Cargo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
	private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String sexo;
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private String comp;
    private String cidade;
    private String uf;
    private String email;
	private TipoPessoaModel tipospessoa;
	private Cargo cargo;
       
    
	public PessoaDTO(PessoaModel obj) {
		
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.rg = obj.getRg();
		this.sexo = obj.getSexo();
		this.cep = obj.getCep();
		this.rua = obj.getRua();
		this.numero = obj.getNumero();
		this.bairro = obj.getBairro();
		this.comp = obj.getComp();
		this.cidade = obj.getCidade();
		this.uf = obj.getUf();
		this.email = obj.getEmail();
		this.tipospessoa = obj.getTipospessoa();
		this.cargo = obj.getCargo();
		
	}



}
