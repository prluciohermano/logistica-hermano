package com.garagecontrolsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.spring.web.json.Json;

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

 
}