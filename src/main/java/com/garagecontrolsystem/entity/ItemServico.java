package com.garagecontrolsystem.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_item_servico")
public class ItemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

   
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column
    private Long quantidade;
    
	@Column(name = "subtotal")
	private BigDecimal subtotal;

 
}