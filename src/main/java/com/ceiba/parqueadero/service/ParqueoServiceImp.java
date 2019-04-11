package com.ceiba.parqueadero.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.dao.ParqueoRepository;
import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;

@Service
public class ParqueoServiceImp implements ParqueoService {

	@Autowired
	protected ParqueoRepository parqueoRepository;

	@Autowired
	protected VehiculoService vehiculoService;

	@Autowired
	protected ParqueaderoService parqueaderoService;

	@Override
	public Parqueo ingresar(Parqueo parqueo) {

		// obtener tiempo actual
		Date fechaActual = new Date();

		// crear el objeto a insertar la db
		parqueo.setFechaIngreso(fechaActual);
		parqueo.setParqueadero(1L);
		// insertarlo en la base de datos

		return parqueoRepository.save(parqueo);
	}

	@Override
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
		Date fechaActual = new Date();
		Long diffInMillies = parqueo.getFechaIngreso().getTime() - fechaActual.getTime();
		Long difEnHoras = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		DecimalFormat df = new DecimalFormat("##");
		df.setRoundingMode(RoundingMode.DOWN);
		Long diasAPagar = Long.valueOf(df.format(difEnHoras / 24));
		Long horasRestantes = difEnHoras % 24;
		if (horasRestantes >= 9) {
			diasAPagar++;
			horasRestantes = 0L;
		} else {
			horasRestantes = Long.valueOf(df.format(horasRestantes));
		}
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
		parqueo.setFechaSalida(fechaActual);
		parqueo.setCosto(Integer.valueOf(df.format(totalAPagar)));

		return parqueo;
	}

	@Override
	public Parqueo findByPlaca(String placa) {

		return parqueoRepository.findByPlaca(placa);
	}

	@Override
	public Parqueo findByPlacaAndFechaSalida(String placa, Date fechaSalida) {

		return parqueoRepository.findByPlacaAndFechaSalida(placa, fechaSalida);
	}

	@Override
	public Parqueo save(Parqueo parqueo) {

		return parqueoRepository.save(parqueo);
	}

}
