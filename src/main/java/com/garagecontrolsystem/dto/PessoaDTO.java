package com.garagecontrolsystem.dto;

import com.garagecontrolsystem.entity.PessoaModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
    private String nome;
    private String cpf;
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private String comp;
    private String cidade;
    private String uf;

//    public PessoaModel toModel(){
//        return new PessoaModel(nome, cpf, cep, rua, numero, bairro, comp, cidade, uf);
//    }

    

	public PessoaDTO(PessoaModel obj) {
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.cep = obj.getCep();
		this.rua = obj.getRua();
		this.numero = obj.getNumero();
		this.bairro = obj.getBairro();
		this.comp = obj.getComp();
		this.cidade = obj.getCidade();
		this.uf = obj.getUf();
		
	}



}
