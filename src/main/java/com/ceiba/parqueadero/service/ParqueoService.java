package com.ceiba.parqueadero.service;

import com.ceiba.parqueadero.model.Parqueo;

public interface ParqueoService {

	Parqueo ingresar(Parqueo parqueo);
	
	Parqueo pagar(Parqueo parqueo);

	Parqueo findByPlaca(String placa);
}
