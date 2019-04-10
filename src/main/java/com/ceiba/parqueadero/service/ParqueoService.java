package com.ceiba.parqueadero.service;

import com.ceiba.parqueadero.model.Parqueo;

public interface ParqueoService {

	Parqueo save(Parqueo parqueo);
	
	Parqueo CalcularCosto(Parqueo parqueo);
}
