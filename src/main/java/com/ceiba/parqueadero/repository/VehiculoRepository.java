package com.ceiba.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.parqueadero.model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long>{

	@SuppressWarnings("unchecked")
	Vehiculo save(Vehiculo vehiculo);
	
	Vehiculo findByPlaca(String placa);
}
