package com.garagecontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garagecontrolsystem.entity.ItemPedido;
import com.garagecontrolsystem.entity.ItemServico;
import com.garagecontrolsystem.entity.Produto;

public interface ItemServicoRepository extends JpaRepository<ItemServico, Long> {

	ItemServico save(ItemServico itemServico);


}
