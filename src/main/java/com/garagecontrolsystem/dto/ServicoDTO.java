package com.garagecontrolsystem.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.garagecontrolsystem.entity.ItemServico;
import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.entity.Servico;
import com.garagecontrolsystem.entity.Veiculo;

import lombok.Data;

@Data
public class ServicoDTO {

	
	private Long id;
    private String descricao;
    private String garantia;
    private String defeito;
    private String observacoes;
    private Date dataInicialServico;
    private Date dataFinalServico;
    private BigDecimal precoServico;
    private BigDecimal total;
    private Pessoa pessoa;
    private String nomePessoa;
    private Veiculo veiculo;
    private List<ItemServico> produtos;
    
    
    
	public ServicoDTO(Servico obj) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.garantia = obj.getGarantia();
		this.defeito = obj.getDefeito();
		this.observacoes = obj.getObservacoes();
		this.dataInicialServico = obj.getDataInicialServico();
		this.dataFinalServico = obj.getDataFinalServico();
		this.precoServico = obj.getPrecoServico();
		this.total = obj.getTotal();
		this.pessoa = obj.getPessoa();
		this.nomePessoa = obj.getNomePessoa();
		this.produtos = obj.getItens();
		this.veiculo = obj.getVeiculo();
	}
    
}