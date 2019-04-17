package com.ceiba.parqueadero.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.util.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class RegistrarIngresoService {


	protected VehiculoService vehiculoService;


	protected ParqueoService parqueoService;


	protected ParqueaderoService parqueaderoService;

	protected ObjectMapper mapper;

	@Autowired
	public RegistrarIngresoService(VehiculoService vehiculoService, ParqueoService parqueoService,
			ParqueaderoService parqueaderoService, ObjectMapper mapper) {
		
		this.vehiculoService = vehiculoService;
		this.parqueoService = parqueoService;
		this.parqueaderoService = parqueaderoService;
		this.mapper = mapper;
	}


	public RestResponse registrarIngresoVehiculo(String vehiculoJson) {
		this.mapper = new ObjectMapper();
		Date fechaActual = new Date();

		Parqueadero parqueadero = parqueaderoService.findById(1L);

		if (parqueadero.getCeldasCarro() <= 0 && parqueadero.getCeldasMoto() <= 0) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "No hay celdas disponibles");
		} else {
			try {
				Vehiculo vehiculo = vehiculoService.convertirYValidarJsonAVehiculo(vehiculoJson);
				vehiculoService.validarPlacaYDiaSemana(vehiculo, fechaActual);
				vehiculoService.validarCeldasDisponibles(parqueadero, vehiculo);
				vehiculoService.validarVehiculoEnParqueadero(vehiculo);
				Parqueo parqueo = new Parqueo();
				Vehiculo vehiculoID = vehiculoService.findByPlaca(vehiculo);
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
