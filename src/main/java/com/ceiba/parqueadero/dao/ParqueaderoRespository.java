package com.ceiba.parqueadero.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.parqueadero.model.Parqueadero;

public interface ParqueaderoRespository extends JpaRepository<Parqueadero, Long> {

	@SuppressWarnings("unchecked")
	Parqueadero save(Parqueadero parqueadero);
}
