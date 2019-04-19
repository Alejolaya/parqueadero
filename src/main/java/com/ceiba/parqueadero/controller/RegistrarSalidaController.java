package com.ceiba.parqueadero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.service.ParqueaderoService;
import com.ceiba.parqueadero.service.ParqueoService;
import com.ceiba.parqueadero.service.RegistrarSalidaService;
import com.ceiba.parqueadero.service.VehiculoService;
import com.ceiba.parqueadero.util.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RegistrarSalidaController {
	@Autowired
	protected VehiculoService vehiculoService;

	@Autowired
	protected ParqueoService parqueoService;

	@Autowired
	protected ParqueaderoService parqueaderoService;

	@Autowired
	protected RegistrarSalidaService registrarSalidaService;
	
	

	protected ObjectMapper mapper;

	@PostMapping(value = "/salida-vehiculos")
	public Response registrarSalida(@RequestBody String parqueoJson) {
		
		return registrarSalidaService.registrarSalida(parqueoJson);

	}


}
