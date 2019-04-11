package com.ceiba.parqueadero.service;

import java.util.Date;

import com.ceiba.parqueadero.model.Parqueo;

public interface ParqueoService {
	
	Parqueo save(Parqueo parqueo);

	Parqueo ingresar(Parqueo parqueo);
	
	Parqueo pagar(Parqueo parqueo);

	Parqueo findByPlaca(String placa);
	
	Parqueo findByPlacaAndFechaSalida(String placa,Date fechaSalida);
}
