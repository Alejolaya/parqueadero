package com.ceiba.parqueadero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.service.ParqueaderoService;
import com.ceiba.parqueadero.service.ParqueoService;
import com.ceiba.parqueadero.service.VehiculoService;
import com.ceiba.parqueadero.util.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RegistrarSalidaController {
	@Autowired
	protected VehiculoService vehiculoService;

	@Autowired
	protected ParqueoService parqueoService;

	@Autowired
	protected ParqueaderoService parqueaderoService;

	protected ObjectMapper mapper;

	@PostMapping(value = "/RegistrarSalida")
	public RestResponse registrarSalida(@RequestBody String ParqueoJson) {
		this.mapper = new ObjectMapper();
		String mensaje= "";
		try {
			Parqueo parqueo = this.mapper.readValue(ParqueoJson, Parqueo.class);
			Parqueo parqueoID = parqueoService.findByPlacaAndFechaSalida(parqueo.getPlaca(),null);
			if(parqueoID == null) {
				throw new Exception("No se encuentra la placa:"+" "+parqueo.getPlaca()+" no se encuentra en el sistema");
			}
			
			parqueo = parqueoService.pagar(parqueoID);
			parqueoService.save(parqueo);
			
			parqueaderoService.liberarCelda(parqueo.getPlaca());
			
			//Calcular monto si es carro cobrarle 
			//Traer tarifa 
			//validar si es moto cobrarle y es alto cilindraje
			
			mensaje="Vehiculo con Placa: "+parqueo.getPlaca()+" paga: $"+parqueo.getCosto();
			
			
			
		}catch (Exception e) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "NO FUE POSIBLE REALIZAR PAGO: "+" "+e.getMessage());
		}
		//TODO:
		//TODO:Validar que la placa exista	
		return new RestResponse(HttpStatus.ACCEPTED.value(), mensaje);
	}
}
