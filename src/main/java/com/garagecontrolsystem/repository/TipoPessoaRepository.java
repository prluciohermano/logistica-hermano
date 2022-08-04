package com.garagecontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.garagecontrolsystem.entity.TipoPessoaModel;

@Repository
@Transactional
public interface TipoPessoaRepository extends JpaRepository<TipoPessoaModel, Long> {

}
