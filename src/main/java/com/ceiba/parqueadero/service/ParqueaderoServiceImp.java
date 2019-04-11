package com.ceiba.parqueadero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.dao.ParqueaderoRepository;
import com.ceiba.parqueadero.dao.VehiculoRepository;
import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;

@Service
public class ParqueaderoServiceImp implements ParqueaderoService {

	@Autowired
	protected ParqueaderoRepository parqueaderoRespository;
	
	@Autowired
	protected VehiculoService vehiculoService;
	
	@Override
	public Parqueadero save(Parqueadero parqueadero) {
		
		return parqueaderoRespository.save(parqueadero);
	}

	@Override
	public void deleteParqueaderoById(Long id) {
		parqueaderoRespository.delete(id);

	}

	@Override
	public Parqueadero findById(Long id) {
		
		return parqueaderoRespository.findOne(id);
	}

	@Override
	public Integer disminuirCeldaMotoDisponible(Long id) {
		Parqueadero parqueadero = parqueaderoRespository.findOne(id);
		parqueadero.setCeldasMoto(parqueadero.getCeldasMoto()-1);
		parqueadero = parqueaderoRespository.save(parqueadero);
		return parqueadero.getCeldasMoto();
	}

	@Override
	public Integer disminuirCeldaCarroDisponible(Long id) {
		Parqueadero parqueadero = parqueaderoRespository.findOne(id);
		parqueadero.setCeldasMoto(parqueadero.getCeldasCarro()-1);
		parqueadero = parqueaderoRespository.save(parqueadero);
		return parqueadero.getCeldasCarro();
	}

	@Override
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
