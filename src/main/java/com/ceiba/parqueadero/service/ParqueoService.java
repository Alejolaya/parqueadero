package com.ceiba.parqueadero.service;

import java.util.Date;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;

public interface ParqueoService {
	/**
	 * Guardar un parqueo
	 * 
	 * @param parqueo
	 * @return
	 */
	Parqueo save(Parqueo parqueo);

	/**
	 * Guarda un parqueo y le adiciona la fecha de ingreso
	 * 
	 * @param parqueo
	 * @return
	 */
	Parqueo ingresar(Parqueo parqueo);

	/**
	 * Busca el vehiculo y el parqueadero, hace el cobro y guarda
	 * 
	 * @param parqueo
	 * @return
	 */
	Parqueo pagar(Parqueo parqueo);

	/**
	 * Busca el parque por placa
	 * 
	 * @param placa
	 * @return
	 */
	Parqueo findByPlaca(String placa);

	/**
	 * Busca parque por placa y que no tengan fecha de salida
	 * 
	 * @param placa
	 * @param fechaSalida
	 * @return
	 */
	Parqueo findByPlacaAndFechaSalida(String placa, Date fechaSalida);

	/**
	 * Calcula la cantidad de horas a pagar
	 * 
	 * @param difEnHoras
	 * @param difEnMinutos
	 * @return
	 */
	Long calcularHorasAPagar(Long difEnHoras, Long difEnMinutos);

	/**
	 * Calcula la cantidad de Dias a pagar
	 * 
	 * @param difEnHoras
	 * @return
	 */
	Long calcularDiasAPagar(Long difEnHoras);

	/**
	 * Hace el calculo de la cantidad de dinero total que debe pagar
	 * 
	 * @param vehiculo
	 * @param parqueadero
	 * @param diasAPagar
	 * @param horasRestantes
	 * @return
	 */
	Long calcularTotalApagar(Vehiculo vehiculo, Parqueadero parqueadero, Long diasAPagar, Long horasRestantes);
	
	/**
	 * Convierte Json a parqueo y el campo placa lo convierte a mayuscula
	 * @param parqueoJson
	 * @return
	 * @throws Exception
	 */
	Parqueo convertirJsonAParqueo(String parqueoJson) throws Exception;
}
