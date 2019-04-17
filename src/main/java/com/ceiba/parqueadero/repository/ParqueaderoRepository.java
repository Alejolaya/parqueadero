package com.ceiba.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.parqueadero.model.Parqueadero;

public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Long> {

	@SuppressWarnings("unchecked")
	Parqueadero save(Parqueadero parqueadero);
}
