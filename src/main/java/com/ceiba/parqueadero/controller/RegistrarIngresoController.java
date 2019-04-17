package com.ceiba.parqueadero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.service.RegistrarIngresoService;
import com.ceiba.parqueadero.util.RestResponse;

@RestController
public class RegistrarIngresoController {
	
	@Autowired
	protected RegistrarIngresoService registrarIngresoService;
	
	

	@PostMapping(value = "/ingreso-vehiculos")
	public RestResponse registrarIngreso(@RequestBody String vehiculoJson)  {


		return registrarIngresoService.registrarIngresoVehiculo(vehiculoJson);

	}







}
