package com.ceiba.parqueadero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.util.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;




@Service
public class RegistrarSalidaService {

	ParqueoService parqueoService;
	ParqueaderoService parqueaderoService;
	protected ObjectMapper mapper;
	
	@Autowired
	public RegistrarSalidaService(ParqueoService parqueoService, ParqueaderoService parqueaderoService) {
		
		this.parqueoService = parqueoService;
		this.parqueaderoService = parqueaderoService;
	}

	public RestResponse registrarSalida(String parqueoJson) {
		this.mapper = new ObjectMapper();
		try {
			Parqueo parqueo = parqueoService.convertirJsonAParqueo(parqueoJson);
			Parqueo parqueoID = parqueoService.findByPlacaAndFechaSalida(parqueo.getPlaca(), null);
			if (parqueoID == null) {
				throw new Exception("No se encuentra la placa:" + " " + parqueo.getPlaca());
			}

			parqueo = parqueoService.pagar(parqueoID);
			parqueoService.save(parqueo);

			parqueaderoService.liberarCelda(parqueo.getPlaca());

			return new RestResponse(HttpStatus.OK.value(),
					"Vehiculo con Placa: " + parqueo.getPlaca() + " paga: $" + parqueo.getCosto());

		} catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),
					"NO FUE POSIBLE REALIZAR PAGO: " + " " + e.getMessage());
		}
		
	}
}
