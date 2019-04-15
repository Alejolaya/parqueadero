package com.ceiba.parqueadero.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.dao.VehiculoRepository;
import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VehiculoServiceImp implements VehiculoService {
	
	@Autowired
	protected VehiculoRepository vehiculoRepository;
	
	@Autowired
	protected ParqueoService parqueoService;
	
	protected ObjectMapper mapper;

	@Override
	public Vehiculo save(Vehiculo vehiculo) {
		
		return this.vehiculoRepository.save(vehiculo);
	}

	@Override
	public void deleteVehiculoById(Long id) {
		this.vehiculoRepository.delete(id);

	}

	@Override
	public Vehiculo findByPlaca(Vehiculo vehiculo) {
		
		return vehiculoRepository.findByPlaca(vehiculo.getPlaca());
	}

	@Override
	public Vehiculo convertirJsonAVehiculo(String vehiculoJson) throws Exception {
		this.mapper = new ObjectMapper();
		
		
		
			Vehiculo vehiculo = this.mapper.readValue(vehiculoJson, Vehiculo.class);
			
			this.validarVehiculo(vehiculo);
			
			//remover espacio
			String placa = vehiculo.getPlaca().trim();
			placa = placa.replaceAll("//s", "");
			//convertir placa a mayuscula
			placa = placa.toUpperCase();
			vehiculo.setPlaca(placa);
			
		
			
			return vehiculo;	
			
		
		
	}
	
	@Override
	public void validarVehiculo(Vehiculo vehiculo) throws Exception {
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

	@Override
	public void validarPlacaYDiaSemana(Vehiculo vehiculo, Date date) throws Exception {
		
		DateFormat formatoDia = new SimpleDateFormat("EEEE");				
		String diaDeLaSemana = formatoDia.format(date);
		
		if(!"SUNDAY".equalsIgnoreCase(diaDeLaSemana) && !"MONDAY".equalsIgnoreCase(diaDeLaSemana)&& 'A'==vehiculo.getPlaca().charAt(0)) {
			throw new Exception("hoy las placas que comienzan con A no pueden ingresar");
		}
	}

	@Override
	public void validarVehiculoEnParqueadero(Vehiculo vehiculo) throws Exception {
		Parqueo parqueoID = parqueoService.findByPlacaAndFechaSalida(vehiculo.getPlaca(),null);
		if (parqueoID != null) {
			if (parqueoID.getFechaSalida() == null) {
				throw new Exception("Vehiculo con la placa ingresada aun no ha reportado salida");
			}
		}
		
	}

	@Override
	public void validarCeldasDisponibles(Parqueadero parqueadero, Vehiculo vehiculo) throws Exception {
		if ("M".equalsIgnoreCase(vehiculo.getTipoVehiculo()) && parqueadero.getCeldasMoto() <= 0) {
			throw new Exception("No hay celdas de moto disponibles");
		}
		if ("C".equalsIgnoreCase(vehiculo.getTipoVehiculo()) && parqueadero.getCeldasCarro() <= 0) {
			throw new Exception("No hay celdas de carro disponibles");
		}
		
	}

}
