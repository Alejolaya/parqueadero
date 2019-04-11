package com.ceiba.parqueadero.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.dao.ParqueoRepository;
import com.ceiba.parqueadero.model.Parqueo;

@Service
public class ParqueoServiceImp implements ParqueoService {

	@Autowired
	protected ParqueoRepository parqueoRepository;
	
	@Override
	public Parqueo ingresar(Parqueo parqueo) {
		
		//obtener tiempo actual
		Date fechaActual = new Date();
		
		//crear el objeto a insertar la db 
		parqueo.setFechaIngreso(fechaActual);
		parqueo.setParqueadero(1L);
		//insertarlo en la base de datos
		
		return parqueoRepository.save(parqueo);
	}

	@Override
	public Parqueo pagar(Parqueo parqueo) {
		//obtener tiempo actual
		//calcular numero de horas
		//consultar vehiculo
		//validar si es moto de alto cc
		//
		return null;
	}

	@Override
	public Parqueo findByPlaca(String placa) {
		
		return parqueoRepository.findByPlaca(placa);
	}

}
