package com.ceiba.parqueadero.service;

import java.util.Date;

import com.ceiba.parqueadero.model.Parqueadero;
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
	
	/**
	 * buscar vehiculo por placa
	 * @param vehiculo
	 * @return
	 */
	Vehiculo findByPlaca(Vehiculo vehiculo);
	
	/**
	 * Valida y corrige el Json
	 * @param vehiculoJson
	 * @return
	 */
	Vehiculo convertirYValidarJsonAVehiculo(String vehiculoJson) throws Exception;
	
	void validarVehiculo(Vehiculo vehiculo) throws Exception;
	
	void validarPlacaYDiaSemana(Vehiculo vehiculo, Date date) throws Exception;
	
	void validarVehiculoEnParqueadero(Vehiculo vehiculo) throws Exception;
	
	void validarCeldasDisponibles(Parqueadero parqueadero, Vehiculo vehiculo) throws Exception;
	
	
}
