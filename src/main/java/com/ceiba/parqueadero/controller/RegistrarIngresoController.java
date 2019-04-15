package com.ceiba.parqueadero.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.service.ParqueaderoService;
import com.ceiba.parqueadero.service.ParqueoService;
import com.ceiba.parqueadero.service.VehiculoService;
import com.ceiba.parqueadero.util.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RegistrarIngresoController {

	@Autowired
	protected VehiculoService vehiculoService;

	@Autowired
	protected ParqueoService parqueoService;

	@Autowired
	protected ParqueaderoService parqueaderoService;

	protected ObjectMapper mapper;

	@PostMapping(value = "/RegistrarIngreso")
	public RestResponse registrarIngreso(@RequestBody String vehiculoJson) throws Exception {

		this.mapper = new ObjectMapper();
		Date fechaActual= new Date();
		
		Parqueadero parqueadero = parqueaderoService.findById(1L);

		if (parqueadero.getCeldasCarro() <= 0 && parqueadero.getCeldasMoto() <= 0) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "No hay celdas disponibles");
		} else {
			try {
				Vehiculo vehiculo = vehiculoService.convertirJsonAVehiculo(vehiculoJson);
				vehiculoService.validarPlacaYDiaSemana(vehiculo, fechaActual);
				vehiculoService.validarCeldasDisponibles(parqueadero, vehiculo);
				vehiculoService.validarVehiculoEnParqueadero(vehiculo);
				Vehiculo vehiculoID = vehiculoService.findByPlaca(vehiculo);
				Parqueo parqueo = new Parqueo();
				if (vehiculoID == null) {
					vehiculoService.save(vehiculo);

				} else {
					vehiculo.setId(vehiculoID.getId());
					vehiculoService.save(vehiculo);

				}
				parqueo.setPlaca(vehiculo.getPlaca());
				parqueoService.ingresar(parqueo);

				if ("M".equalsIgnoreCase(vehiculo.getTipoVehiculo())) {
					parqueaderoService.disminuirCeldaMotoDisponible(1L);
				} else {
					parqueaderoService.disminuirCeldaCarroDisponible(1L);
				}

				return new RestResponse(HttpStatus.OK.value(), "Vehiculo Registrado con exito");

			} catch (Exception e) {
				return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),
						"NO SE REGISTRO INGRESO" + " " + e.getMessage());
			}
		}

	}





}
