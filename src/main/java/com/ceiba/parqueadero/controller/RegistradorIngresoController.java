package com.ceiba.parqueadero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.service.RegistradorIngresoService;
import com.ceiba.parqueadero.util.Response;

@RestController
public class RegistradorIngresoController {
	
	@Autowired
	protected RegistradorIngresoService registradorIngresoService;
	
	

	@PostMapping(value = "/ingreso-vehiculos")
	public Response registrarIngreso(@RequestBody String vehiculoJson)  {


		return registradorIngresoService.registrarIngresoVehiculo(vehiculoJson);

	}







}
