package com.ceiba.parqueadero.service;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Vehiculo;

public interface ParqueaderoService {

	/**
	 * Guarda el parqueadero
	 * @param parqueadero
	 * @return
	 */
	Parqueadero save(Parqueadero parqueadero);
	
	
	/**
	 * Borra el parqueadero
	 * @param id
	 */
	void deleteParqueaderoById(Long id);
	
	/**
	 * Encontrar el parqueadero
	 * @param id
	 * @return
	 */
	Parqueadero findById(Long id);
	
	/**
	 * Disminuye la cantidad de celdas disponibles de carro y retorna la cantidad restante
	 * @return
	 */
	Integer disminuirCeldaMotoDisponible(Long id);
	/**
	 * Disminuye la cantidad de celdas disponibles de moto y retorna la cantidad restante
	 * @return
	 */
	Integer disminuirCeldaCarroDisponible(Long id);
	
	
}
