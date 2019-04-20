package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.repository.ParqueaderoRepository;
import com.ceiba.parqueadero.repository.ParqueoRepository;
import com.ceiba.parqueadero.repository.VehiculoRepository;
import com.ceiba.parqueadero.service.ParqueaderoService;
import com.ceiba.parqueadero.service.RegistrarIngresoService;
import com.ceiba.parqueadero.service.VehiculoService;


@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest
public class ParqueoIntegracionTests {

	private static final String BBC123 = "BBC123";

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
	
	@Autowired
	VehiculoService vehiculoService;
	
	
	
	
	


	///////////////////////Registrar motos y carros
	@Test
	public final void ingresoMotoIntegracionTest() throws JSONException {
		//given
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put("placa", " bbc123 ");
		vehiculoJson.put("tipoVehiculo", "M");
		vehiculoJson.put("cilindraje", 1000);
		//when
		registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		//then
		Vehiculo vehiculo = vehiculoRepository.findByPlaca(BBC123);
		Parqueo parqueo = parqueoRepository.findByPlacaAndFechaSalida(BBC123, null);
		Parqueadero parqueadero = parqueaderoRepository.findOne(1L);
		
		assertThat(vehiculo.getTipoVehiculo()).isEqualTo("M");
		assertThat(parqueo.getPlaca()).isEqualTo(BBC123);
		assertThat(parqueadero.getCeldasMoto()).isEqualTo(9);
			
	}

}
