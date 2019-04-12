package com.ceiba.parqueadero.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.dao.VehiculoRepository;
import com.ceiba.parqueadero.model.Vehiculo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VehiculoServiceImp implements VehiculoService {
	
	@Autowired
	protected VehiculoRepository vehiculoRepository;
	
	protected ObjectMapper mapper;

	@Override
	public Vehiculo save(Vehiculo vehiculo) {
		
		return this.vehiculoRepository.save(vehiculo);
	}

	@Override
	public void deleteVehiculoById(Long id) {
		this.vehiculoRepository.delete(id);

	}

	@Override
	public Vehiculo findByPlaca(Vehiculo vehiculo) {
		
		return vehiculoRepository.findByPlaca(vehiculo.getPlaca());
	}

	@Override
	public Vehiculo convertirJsonAVehiculo(String vehiculoJson) throws Exception {
		this.mapper = new ObjectMapper();
		
			Vehiculo vehiculo = this.mapper.readValue(vehiculoJson, Vehiculo.class);
			//remover espacio
			String placa = vehiculo.getPlaca().trim();
			placa = placa.replaceAll("//s", "");
			//convertir placa a mayuscula
			placa = placa.toUpperCase();
			vehiculo.setPlaca(placa);
			
			if (vehiculo.getPlaca() == null) {
				throw new Exception("La campo Placa no puede estar vacio");
			}
			if (vehiculo.getTipoVehiculo() == null) {
				throw new Exception("La campo Tipo Vehiculo no puede estar vacio");
			}
			if ("M".equalsIgnoreCase(vehiculo.getTipoVehiculo()) && vehiculo.getCilindraje() == 0) {
				throw new Exception("Si es una moto CC no puede estar vacio");
			}		
			
			return vehiculo;	
			
		
		
	}

}
