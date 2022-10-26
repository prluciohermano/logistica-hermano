package com.garagecontrolsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.garagecontrolsystem.entity.Departamento;


@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

	@Query(value = "select d from Departamento d where upper(trim(d.tipo_dep)) like %?1%")
	List<Departamento> buscarPorDepartamento(String nomeDepartamento);
	
}
