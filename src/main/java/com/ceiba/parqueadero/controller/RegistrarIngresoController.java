package com.ceiba.parqueadero.controller;

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
		Parqueadero parqueadero = parqueaderoService.findById(1L);
		Vehiculo vehiculoID = new Vehiculo();
		if (parqueadero.getCeldasCarro() <= 0 && parqueadero.getCeldasMoto() <= 0) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "No hay celdas disponibles");
		} else {
			try {
				Vehiculo vehiculo = this.mapper.readValue(vehiculoJson, Vehiculo.class);

				validarVehiculo(vehiculo);

				validarCeldasDisponibles(parqueadero, vehiculo);
				// TODO: Validar que si la placa comienza con A y es Dom-lunes no se le permite
				// el ingreso
				// TODO: Validar si la placa ya se encuentra en el parqueadero
				validarVehiculoEnParqueadero(vehiculo);
				vehiculoID = vehiculoService.findByPlaca(vehiculo);
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

				return new RestResponse(HttpStatus.ACCEPTED.value(), "Vehiculo Registrado con exito");

			} catch (Exception e) {
				return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),
						"NO SE REGISTRO INGRESO"+ " " + e.getMessage() );
			}
		}

	}

	private void validarVehiculoEnParqueadero(Vehiculo vehiculo) throws Exception {
		Parqueo parqueoID = parqueoService.findByPlaca(vehiculo.getPlaca());
		if (parqueoID != null) {
			if (parqueoID.getFechaSalida() == null) {
				throw new Exception("Vehiculo con la placa ingresada aun no ha reportado salida");
			}
		}
	}

	private void validarCeldasDisponibles(Parqueadero parqueadero, Vehiculo vehiculo) throws Exception {
		if ("M".equalsIgnoreCase(vehiculo.getTipoVehiculo()) && parqueadero.getCeldasMoto() <= 0) {
			throw new Exception("No hay celdas de moto disponibles");
		}
		if ("C".equalsIgnoreCase(vehiculo.getTipoVehiculo()) && parqueadero.getCeldasCarro() <= 0) {
			throw new Exception("No hay celdas de carro disponibles");
		}

	}

	private void validarVehiculo(Vehiculo vehiculo) throws Exception {
		if (vehiculo.getPlaca() == null) {
			throw new Exception("La campo Placa no puede estar vacio");
		}
		if (vehiculo.getTipoVehiculo() == null) {
			throw new Exception("La campo Tipo Vehiculo no puede estar vacio");
		}
		if ("M".equalsIgnoreCase(vehiculo.getTipoVehiculo()) && vehiculo.getCilindraje() == 0) {
			throw new Exception("Si es una moto CC no puede estar vacio");
		}
	}

}
