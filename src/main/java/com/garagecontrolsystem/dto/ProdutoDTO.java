package com.garagecontrolsystem.dto;

import java.math.BigDecimal;
import java.util.Set;

import com.garagecontrolsystem.entity.Produto;
import com.garagecontrolsystem.entity.Servico;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProdutoDTO {
	
	private Long id;
	private String nomeProduto;
    private String descricao;
    private BigDecimal preco;
//    private Set<Servico> servicos;
    
    
    public ProdutoDTO(Produto obj) {
		
		this.id = obj.getId();
		this.nomeProduto = obj.getNomeProduto();
		this.descricao = obj.getDescricao();
		this.preco = obj.getPreco();
//		this.servicos = obj.getServicos();
    }
}
