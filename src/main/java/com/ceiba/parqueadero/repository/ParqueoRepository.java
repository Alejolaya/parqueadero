package com.ceiba.parqueadero.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.parqueadero.model.Parqueo;

public interface ParqueoRepository extends JpaRepository<Parqueo, Long>{

	@SuppressWarnings("unchecked")
	Parqueo save(Parqueo parqueo);

	Parqueo findByPlaca(String placa);

	Parqueo findByPlacaAndFechaSalida(String placa, Date fechaSalida);
}
