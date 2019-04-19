package com.ceiba.parqueadero.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.repository.VehiculoRepository;
import com.ceiba.parqueadero.util.ValidarVehiculoException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VehiculoService  {

//	@Autowired
	protected VehiculoRepository vehiculoRepository;
//	@Autowired
	protected ParqueoService parqueoService;
	
	protected ObjectMapper mapper;

	@Autowired
	public VehiculoService(VehiculoRepository vehiculoRepository, @Lazy ParqueoService parqueoService) {

		this.vehiculoRepository = vehiculoRepository;
		this.parqueoService = parqueoService;
		//this.mapper = mapper;
	}

	
	public Vehiculo save(Vehiculo vehiculo) {

		return this.vehiculoRepository.save(vehiculo);
	}

	
	public void deleteVehiculoById(Long id) {
		this.vehiculoRepository.delete(id);

	}

	
	public Vehiculo findByPlaca(Vehiculo vehiculo) {

		return vehiculoRepository.findByPlaca(vehiculo.getPlaca());
	}

	
	public Vehiculo convertirYValidarJsonAVehiculo(String vehiculoJson) throws ValidarVehiculoException {
		ObjectMapper mapper = new ObjectMapper();

		Vehiculo vehiculo = new Vehiculo();
		try {
			vehiculo = mapper.readValue(vehiculoJson, Vehiculo.class);
		} catch (Exception e) {
			throw new ValidarVehiculoException("datos invalidos", 0);
		}

		this.validarVehiculo(vehiculo);

		// remover espacio
		String placa = vehiculo.getPlaca().trim();
		placa = placa.replaceAll("//s", "");
		// convertir placa a mayuscula
		placa = placa.toUpperCase();
		vehiculo.setPlaca(placa);

		return vehiculo;

	}

	
	public void validarVehiculo(Vehiculo vehiculo) throws ValidarVehiculoException {
		if (vehiculo.getPlaca() == null) {
			throw new ValidarVehiculoException("La campo Placa no puede estar vacio", 0);
		}
		if (vehiculo.getTipoVehiculo() == null) {
			throw new ValidarVehiculoException("La campo Tipo Vehiculo no puede estar vacio", 0);
		}
		if ("M".equalsIgnoreCase(vehiculo.getTipoVehiculo()) && vehiculo.getCilindraje() == 0) {
			throw new ValidarVehiculoException("Si es una moto CC no puede estar vacio", 0);
		}
	}

	
	public void validarPlacaYDiaSemana(Vehiculo vehiculo, Date date) throws ValidarVehiculoException {

		DateFormat formatoDia = new SimpleDateFormat("EEEE");
		String diaDeLaSemana = formatoDia.format(date);

		if (!"SUNDAY".equalsIgnoreCase(diaDeLaSemana) && !"MONDAY".equalsIgnoreCase(diaDeLaSemana)
				&& 'A' == vehiculo.getPlaca().charAt(0)) {
			throw new ValidarVehiculoException("hoy las placas que comienzan con A no pueden ingresar", 0);
		}
	}

	
	public void validarVehiculoEnParqueadero(Vehiculo vehiculo) throws ValidarVehiculoException {
		Parqueo parqueoID = parqueoService.findByPlacaAndFechaSalida(vehiculo.getPlaca(), null);
		if (parqueoID != null) {
			if (parqueoID.getFechaSalida() == null) {
				throw new ValidarVehiculoException("Vehiculo con la placa ingresada aun no ha reportado salida", 0);
			}
		}

	}

	
	public void validarCeldasDisponibles(Parqueadero parqueadero, Vehiculo vehiculo) throws ValidarVehiculoException {
		if ("M".equalsIgnoreCase(vehiculo.getTipoVehiculo()) && parqueadero.getCeldasMoto() <= 0) {
			throw new ValidarVehiculoException("No hay celdas de moto disponibles", 0);
		}
		if ("C".equalsIgnoreCase(vehiculo.getTipoVehiculo()) && parqueadero.getCeldasCarro() <= 0) {
			throw new ValidarVehiculoException("No hay celdas de carro disponibles", 0);
		}

	}

}
