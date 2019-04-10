package com.ceiba.parqueadero.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.parqueadero.model.Parqueo;

public interface ParqueoRepository extends JpaRepository<Parqueo, Long>{

	@SuppressWarnings("unchecked")
	Parqueo save(Parqueo parqueo);
}
