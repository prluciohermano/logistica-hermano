package com.garagecontrolsystem.repository;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.garagecontrolsystem.entity.TelefoneModel;

@Repository
@Transactional
public interface TelefoneRepository extends JpaRepository<TelefoneModel, UUID> {

	@Query(value = "select t from TelefoneModel t where t.pessoa.id = ?1")
	public List<TelefoneModel> getTelefones(UUID pessoaid);
}
