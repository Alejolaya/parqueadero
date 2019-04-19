package com.ceiba.parqueadero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.repository.ParqueaderoRepository;

@Service
public class ParqueaderoService {


	protected ParqueaderoRepository parqueaderoRespository;
	
	protected VehiculoService vehiculoService;
	
	
	
	@Autowired
	public ParqueaderoService(ParqueaderoRepository parqueaderoRespository, VehiculoService vehiculoService) {
		
		this.parqueaderoRespository = parqueaderoRespository;
		this.vehiculoService = vehiculoService;
	}


	public Parqueadero save(Parqueadero parqueadero) {
		
		return parqueaderoRespository.save(parqueadero);
	}


	public void deleteParqueaderoById(Long id) {
		parqueaderoRespository.delete(id);

	}


	public Parqueadero findById(Long id) {
		
		return parqueaderoRespository.findOne(id);
	}


	public Integer disminuirCeldaMotoDisponible(Long id) {
		Parqueadero parqueadero = parqueaderoRespository.findOne(id);
		parqueadero.setCeldasMoto(parqueadero.getCeldasMoto()-1);
		parqueadero = parqueaderoRespository.save(parqueadero);
		return parqueadero.getCeldasMoto();
	}


	public Integer disminuirCeldaCarroDisponible(Long id) {
		Parqueadero parqueadero = parqueaderoRespository.findOne(id);
		parqueadero.setCeldasCarro(parqueadero.getCeldasCarro()-1);
		parqueadero = parqueaderoRespository.save(parqueadero);
		return parqueadero.getCeldasCarro();
	}


	public void liberarCelda(String placa) {
		
		Vehiculo vehiculo =new Vehiculo();
		vehiculo.setPlaca(placa);
		vehiculo = vehiculoService.findByPlaca(vehiculo);
		
		Parqueadero parqueadero = parqueaderoRespository.findOne(1L);
		
		if ("M".equalsIgnoreCase(vehiculo.getTipoVehiculo())) {
			parqueadero.setCeldasMoto(parqueadero.getCeldasMoto()+1);
		}else {
			parqueadero.setCeldasMoto(parqueadero.getCeldasCarro()+1);
		}
		
		parqueaderoRespository.save(parqueadero);
	}

	

}
