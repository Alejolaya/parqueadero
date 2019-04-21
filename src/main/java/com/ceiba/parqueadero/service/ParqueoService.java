package com.ceiba.parqueadero.service;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.repository.ParqueoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ParqueoService {

	protected ParqueoRepository parqueoRepository;

	protected VehiculoService vehiculoService;

	protected ParqueaderoService parqueaderoService;

	protected TiempoService tiempoService;

	@Autowired
	public ParqueoService(ParqueoRepository parqueoRepository, VehiculoService vehiculoService,
			ParqueaderoService parqueaderoService, TiempoService tiempoService) {

		this.parqueoRepository = parqueoRepository;
		this.vehiculoService = vehiculoService;
		this.parqueaderoService = parqueaderoService;
		this.tiempoService = tiempoService;

	}

	public Parqueo ingresar(Parqueo parqueo, LocalDateTime fechaActual) {

		// crear el objeto a insertar la db
		parqueo.setFechaIngreso(fechaActual);
		parqueo.setParqueadero(1L);
		// insertarlo en la base de datos

		return parqueoRepository.save(parqueo);
	}

	public Parqueo pagar(Parqueo parqueo) {
		// obtener tiempo actual
		// calcular numero de horas
		// consultar vehiculo
		// validar si es moto de alto cc
		//

		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(parqueo.getPlaca());
		vehiculo = vehiculoService.findByPlaca(vehiculo);
		Parqueadero parqueadero = parqueaderoService.findById(1L);

		// Calcular diferencia de fechas
		LocalDateTime fechaActual = LocalDateTime.now();
		LocalDateTime fechaIngreso = parqueo.getFechaIngreso();
		
		Long diffInMillies = calcularDiferenciaEnMiliSeg(tiempoService.converToDate(fechaActual), tiempoService.converToDate(fechaIngreso));
		Long difEnHoras = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		Long difEnMinutos = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
		DecimalFormat df = new DecimalFormat("##");
		df.setRoundingMode(RoundingMode.DOWN);

		Long diasAPagar = calcularDiasAPagar(difEnHoras);

		Long horasRestantes = calcularHorasAPagar(difEnHoras, difEnMinutos);

		Long totalAPagar = calcularTotalApagar(vehiculo, parqueadero, diasAPagar, horasRestantes);

		parqueo.setFechaSalida(fechaActual);
		parqueo.setCosto(Integer.valueOf(df.format(totalAPagar)));

		return parqueo;
	}

	public Long calcularDiferenciaEnMiliSeg(Date fechaActual, Date fechaIngreso) {
		return fechaActual.getTime() - fechaIngreso.getTime();

	}

	public Long calcularDiasAPagar(Long difEnHoras) {
		DecimalFormat df = new DecimalFormat("##");

		Long diasAPagar = Long.valueOf(df.format(difEnHoras / 24));

		if (difEnHoras % 24 >= 9) {
			diasAPagar++;

		}
		return diasAPagar;
	}

	public Long calcularHorasAPagar(Long difEnHoras, Long difEnMinutos) {
		DecimalFormat df = new DecimalFormat("##");
		Long horasRestantes = difEnHoras % 24;
		if (horasRestantes >= 9) {
			horasRestantes = 0L;
		} else {
			df.setRoundingMode(RoundingMode.UP);
			horasRestantes = Long.valueOf(df.format(horasRestantes));
		}

		if (difEnMinutos < 60) {
			horasRestantes++;
		} else {
			Long minutosRestantes = difEnMinutos % 60;
			if (minutosRestantes > 0) {
				horasRestantes++;
			}
		}
		return horasRestantes;
	}

	public Long calcularTotalApagar(Vehiculo vehiculo, Parqueadero parqueadero, Long diasAPagar, Long horasRestantes) {
		Long horasAPagar = horasRestantes;
		Long totalAPagar;
		if ("M".equalsIgnoreCase(vehiculo.getTipoVehiculo())) {
			if (vehiculo.getCilindraje() >= 500) {
				totalAPagar = diasAPagar * parqueadero.getMotoDia() + horasAPagar * parqueadero.getMotoHora()
						+ parqueadero.getAltoCc();
			} else {
				totalAPagar = diasAPagar * parqueadero.getMotoDia() + horasAPagar * parqueadero.getMotoHora();
			}

		} else {
			totalAPagar = diasAPagar * parqueadero.getCarroDia() + horasAPagar * parqueadero.getCarroHora();
		}
		return totalAPagar;
	}

	public Parqueo findByPlaca(String placa) {

		return parqueoRepository.findByPlaca(placa);
	}

	public Parqueo findByPlacaAndFechaSalida(String placa, Date fechaSalida) {

		return parqueoRepository.findByPlacaAndFechaSalida(placa, fechaSalida);
	}

	public Parqueo save(Parqueo parqueo) {

		return parqueoRepository.save(parqueo);
	}

	public Parqueo convertirJsonAParqueo(String parqueoJson)    {
		ObjectMapper mapper = new ObjectMapper();

		Parqueo parqueo;
		try {
			parqueo = mapper.readValue(parqueoJson, Parqueo.class);
		} catch (IOException e) {
			return null;
		}
		// remover espacios
		String placa = parqueo.getPlaca().trim();
		placa = placa.replaceAll("//s", "");
		// Convertir la placa a mayuscula

		placa = placa.toUpperCase();
		parqueo.setPlaca(placa);
		// convertir placa a mayuscula
		placa = placa.toUpperCase();
		parqueo.setPlaca(placa);

		return parqueo;
	}

}
