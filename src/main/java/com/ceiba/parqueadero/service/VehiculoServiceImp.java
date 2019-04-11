package com.ceiba.parqueadero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.dao.VehiculoRepository;
import com.ceiba.parqueadero.model.Vehiculo;

@Service
public class VehiculoServiceImp implements VehiculoService {
	
	@Autowired
	protected VehiculoRepository vehiculoRepository;

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

}
