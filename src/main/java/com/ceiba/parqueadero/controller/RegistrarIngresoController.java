package com.ceiba.parqueadero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.service.VehiculoService;
import com.ceiba.parqueadero.util.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RegistrarIngresoController {
	
	@Autowired
	protected VehiculoService vehiculoService;

	protected ObjectMapper mapper;
	@RequestMapping(value = "/RegistrarIngreso", method = RequestMethod.POST)
	public RestResponse registrarIngreso(@RequestBody String vehiculoJson) throws Exception  {
		
		this.mapper = new ObjectMapper();
		try {
		Vehiculo vehiculo = this.mapper.readValue(vehiculoJson, Vehiculo.class);
		
		validarVehiculo(vehiculo);
		vehiculoService.save(vehiculo);
		return new RestResponse(HttpStatus.ACCEPTED.value(),"Vehiculo Registrado con exito");
		}catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),e.getMessage()+" "+"NO SE REGISTRO INGRESO");
		}
		
		
	}

	private void validarVehiculo(Vehiculo vehiculo) throws Exception {
		if (vehiculo.getPlaca()==null) {
			throw new Exception("La campo Placa no puede estar vacio");
		}
		if (vehiculo.getTipoVehiculo()==null) {
			throw new Exception("La campo Tipo Vehiculo no puede estar vacio");
		}
		if ("M".equalsIgnoreCase(vehiculo.getTipoVehiculo()) && vehiculo.getCilindraje() == 0) {
			throw new Exception("Si es una moto CC no puede estar vacio");
		}
	}
	
}
