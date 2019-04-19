package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.repository.ParqueaderoRepository;
import com.ceiba.parqueadero.repository.ParqueoRepository;
import com.ceiba.parqueadero.repository.VehiculoRepository;
import com.ceiba.parqueadero.service.ParqueaderoService;
import com.ceiba.parqueadero.service.ParqueoService;
import com.ceiba.parqueadero.service.RegistrarIngresoService;
import com.ceiba.parqueadero.service.VehiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {VehiculoService.class,ParqueaderoService.class,ParqueoService.class,ParqueoRepository.class,ParqueaderoRepository.class,VehiculoRepository.class})
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
	
	@Autowired
	VehiculoService vehiculoService;
	
	
	
	
	
//	@Before
//	public void iniciarTest() throws Exception {
//		//id/PrecioHoraMoto/PrecioDiaMoto/CostoAltoCc/PrecioHoraCarro/PrecioHoraDia/CeldasMoto/CeldasCarro
////		Parqueadero parqueadero = new Parqueadero(0L, 500, 4000, 2000, 1000, 8000, 10, 20);
////		parqueaderoRepository.save(parqueadero);
//	}
//	
//	@After
//	public void finalizarTest() throws Exception{
////		parqueaderoRepository.delete(1L);
//	}
	///////////////////////Registrar motos y carros
	@Test
	public final void IntegracionIngresoMotoYCarrotest() throws Exception {

		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put("placa", " bbc123 ");
		vehiculoJson.put("tipoVehiculo", "M");
		vehiculoJson.put("cilindraje", 1000);
		
		registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		
		Vehiculo vehiculo = vehiculoRepository.findByPlaca("BBC123");
		
		assertThat(vehiculo.getTipoVehiculo()).isEqualTo("M");
			
	}

}
