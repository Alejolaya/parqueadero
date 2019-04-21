package com.ceiba.parqueadero.service;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.util.Response;
import com.ceiba.parqueadero.util.ValidarVehiculoException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class RegistradorIngresoService {

	private static final int NOT_ACCEPTABLE = 406;

	private static final int OK = 200;

	protected VehiculoService vehiculoService;

	protected ParqueoService parqueoService;

	protected ParqueaderoService parqueaderoService;

	protected ObjectMapper mapper;
	
	protected TiempoService tiempoService;

	@Autowired
	public RegistradorIngresoService(VehiculoService vehiculoService, ParqueoService parqueoService,
			ParqueaderoService parqueaderoService, ObjectMapper mapper,TiempoService tiempoService) {

		this.vehiculoService = vehiculoService;
		this.parqueoService = parqueoService;
		this.parqueaderoService = parqueaderoService;
		this.mapper = mapper;
		this.tiempoService = tiempoService;
		
	}

	public Response registrarIngresoVehiculo(String vehiculoJson)  {
		this.mapper = new ObjectMapper();
		Clock clock = Clock.systemDefaultZone();
		LocalDateTime fechaActual = tiempoService.tiempoActualTipoLocalDateTime(clock);

		Parqueadero parqueadero = parqueaderoService.findById(1L);

		if (parqueadero.getCeldasCarro() <= 0 && parqueadero.getCeldasMoto() <= 0) {
			return new Response(NOT_ACCEPTABLE, "No hay celdas disponibles");
		} else {
			try {
				Vehiculo vehiculo = validarVehiculo(vehiculoJson, fechaActual, parqueadero);
				parquear(vehiculo,fechaActual);
				disminuirCeldasDisponibles(vehiculo);

				return new Response(OK, "Vehiculo Registrado con exito");

			}catch (ValidarVehiculoException e) {
				return new Response(NOT_ACCEPTABLE,
						"NO SE REGISTRO INGRESO" + " " + e.getMessage());
			}
		}
	}

	private void disminuirCeldasDisponibles(Vehiculo vehiculo) {
		if ("M".equalsIgnoreCase(vehiculo.getTipoVehiculo())) {
			parqueaderoService.disminuirCeldaMotoDisponible(1L);
		} else {
			parqueaderoService.disminuirCeldaCarroDisponible(1L);
		}
	}

	private void parquear(Vehiculo vehiculo, LocalDateTime fechaActual) {
		Parqueo parqueo = new Parqueo();
		Vehiculo vehiculoID = vehiculoService.findByPlaca(vehiculo);
		if (vehiculoID == null) {
			vehiculoService.save(vehiculo);

		} else {
			vehiculo.setId(vehiculoID.getId());
			vehiculoService.save(vehiculo);

		}
		parqueo.setPlaca(vehiculo.getPlaca());
		parqueoService.ingresar(parqueo,fechaActual);
	}

	private Vehiculo validarVehiculo(String vehiculoJson, LocalDateTime fechaActual, Parqueadero parqueadero) throws ValidarVehiculoException  {
		Vehiculo vehiculo = vehiculoService.convertirYValidarJsonAVehiculo(vehiculoJson);
		vehiculoService.validarPlacaYDiaSemana(vehiculo, fechaActual);
		vehiculoService.validarCeldasDisponibles(parqueadero, vehiculo);
		vehiculoService.validarVehiculoEnParqueadero(vehiculo);
		return vehiculo;
	}

}
