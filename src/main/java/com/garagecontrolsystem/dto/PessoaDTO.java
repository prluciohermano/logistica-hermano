package com.garagecontrolsystem.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.garagecontrolsystem.entity.PessoaModel;

import lombok.Data;

@Data
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

    public PessoaModel toModel(){
        return new PessoaModel(nome, cpf, cep, rua, numero, bairro, comp, cidade, uf);
    }

    private PessoaDTO convert(PessoaModel pessoa) {
        BeanUtils.copyProperties(pessoa, this, nome, cpf, cep, rua, numero, bairro, comp, cidade, uf);
        return this;
    }

    public List<PessoaDTO> convertList(List<PessoaModel> pessoaList){
       PessoaDTO pessoaDTO = new PessoaDTO();
       List<PessoaDTO> pessoaDTOList = new ArrayList<>();
       pessoaList.forEach(u -> {
           pessoaDTOList.add(pessoaDTO.convert(u));
       });
       return pessoaDTOList;
    }



}
