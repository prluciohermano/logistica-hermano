package com.garagecontrolsystem.dto;

import java.math.BigDecimal;

import com.garagecontrolsystem.entity.Produto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProdutoDTO {
	
	private Long id;
    private String descricao;
    private BigDecimal preco;
    
    
    public ProdutoDTO(Produto obj) {
		
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.preco = obj.getPreco();
    }
}
