package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.ceiba.parqueadero.dao.ParqueaderoRepository;
import com.ceiba.parqueadero.dao.ParqueoRepository;
import com.ceiba.parqueadero.dao.VehiculoRepository;
import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.service.ParqueaderoService;
import com.ceiba.parqueadero.service.RegistrarIngresoService;


@SpringBootTest
public class ParqueoIntegracionTests {

	@Autowired
	ParqueaderoRepository parqueaderoRepository;
	
	@Autowired
	ParqueaderoService parqueaderoService;
	
	@Autowired 
	RegistrarIngresoService registrarIngresoService;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Autowired
	ParqueoRepository parqueoRepository;
	
	
	
	
	@Before
	public void iniciarTest() throws Exception {
		//id/PrecioHoraMoto/PrecioDiaMoto/CostoAltoCc/PrecioHoraCarro/PrecioHoraDia/CeldasMoto/CeldasCarro
//		Parqueadero parqueadero = new Parqueadero(0L, 500, 4000, 2000, 1000, 8000, 10, 20);
//		parqueaderoRepository.save(parqueadero);
	}
	
	@After
	public void finalizarTest() throws Exception{
//		parqueaderoRepository.delete(1L);
	}
	///////////////////////Registrar motos y carros
	@Test
	public final void IntegracionIngresoMotoYCarrotest() throws JSONException {
//		Parqueadero parqueadero = new Parqueadero(0L, 500, 4000, 2000, 1000, 8000, 10, 20);
//		parqueaderoService.save(parqueadero);
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put("placa", " abc123 ");
		vehiculoJson.put("tipoVehiculo", "M");
		vehiculoJson.put("cilindraje", 1000);
		
		registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		
		Vehiculo vehiculo = vehiculoRepository.findByPlaca("ABC123");
		
		assertThat(vehiculo.getTipoVehiculo()).isEqualTo("M");
			
	}

}
