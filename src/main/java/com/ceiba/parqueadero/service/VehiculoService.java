package com.ceiba.parqueadero.service;

import com.ceiba.parqueadero.model.Vehiculo;

public interface VehiculoService {

	/**
	 * Guarda vehiculo
	 * @param vehiculo
	 * @return
	 */
	Vehiculo save(Vehiculo vehiculo);
	/**
	 * Borra Vehiculo por id
	 * @param id
	 */
	void deleteVehiculoById(Long id);
}
