package com.ceiba.parqueadero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.dao.ParqueaderoRespository;
import com.ceiba.parqueadero.model.Parqueadero;

@Service
public class ParqueaderoServiceImp implements ParqueaderoService {

	@Autowired
	protected ParqueaderoRespository parqueaderoRespository;
	
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

	

}
