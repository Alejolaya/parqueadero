package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
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
import com.ceiba.parqueadero.service.RegistradorIngresoService;
import com.ceiba.parqueadero.service.TiempoService;
import com.ceiba.parqueadero.service.VehiculoService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IngresarMotoYCarrosTest {


	private static final String BBB222 = "BBB222";

	private static final String BBB111 = "BBB111";


	@Autowired
	ParqueaderoRepository parqueaderoRepository;
	
	@Autowired
	ParqueaderoService parqueaderoService;
	
	@Autowired 
	RegistradorIngresoService registrarIngresoService;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Autowired
	ParqueoRepository parqueoRepository;
	
	@Autowired
	VehiculoService vehiculoService;
	
	@Autowired
	TiempoService tiempoService;
	
	
	

	@After
	public final void despuesDeLosTest() {
		vehiculoRepository.deleteAll();
		parqueoRepository.deleteAll();
		parqueaderoRepository.save(new Parqueadero(1L,500,4000,2000,1000,8000,10,20));
	}

	///////////////////////Registrar motos y carros
	@Test
	public final void ingresoMotoIntegracionTest() throws JSONException {
		//given
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put("placa", " bbb111 ");
		vehiculoJson.put("tipoVehiculo", "M");
		vehiculoJson.put("cilindraje", 1000);
		//when
		registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		//then
		Vehiculo vehiculo = vehiculoRepository.findByPlaca(BBB111);
		Parqueo parqueo = parqueoRepository.findByPlacaAndFechaSalida(BBB111, null);
		Parqueadero parqueadero = parqueaderoRepository.findOne(1L);
		
		assertThat(vehiculo.getTipoVehiculo()).isEqualTo("M");
		assertThat(parqueo.getPlaca()).isEqualTo(BBB111);
		assertThat(parqueadero.getCeldasMoto()).isEqualTo(9);
			
	}
	
	@Test
	public final void ingresoCarroIntegracionTest() throws JSONException {
		//given
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put("placa", " BBB222 ");
		vehiculoJson.put("tipoVehiculo", "C");
		vehiculoJson.put("cilindraje", 0);
		//when
		registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		//then
		Vehiculo vehiculo = vehiculoRepository.findByPlaca(BBB222);
		Parqueo parqueo = parqueoRepository.findByPlacaAndFechaSalida(BBB222, null);
		Parqueadero parqueadero = parqueaderoRepository.findOne(1L);
		
		assertThat(vehiculo.getTipoVehiculo()).isEqualTo("C");
		assertThat(parqueo.getPlaca()).isEqualTo(BBB222);
		assertThat(parqueadero.getCeldasCarro()).isEqualTo(19);
			
	}
	


}
