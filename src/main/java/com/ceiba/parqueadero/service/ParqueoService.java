package com.ceiba.parqueadero.service;

import java.util.Date;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;

public interface ParqueoService {
	
	Parqueo save(Parqueo parqueo);

	Parqueo ingresar(Parqueo parqueo);
	
	Parqueo pagar(Parqueo parqueo);

	Parqueo findByPlaca(String placa);
	
	Parqueo findByPlacaAndFechaSalida(String placa,Date fechaSalida);
	
	Long calcularHorasAPagar(Long difEnHoras, Long difEnMinutos);
	
	Long calcularDiasAPagar(Long difEnHoras);
	
	Long calcularTotalApagar(Vehiculo vehiculo, Parqueadero parqueadero, Long diasAPagar, Long horasRestantes);
}
