package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.repository.ParqueaderoRepository;
import com.ceiba.parqueadero.repository.ParqueoRepository;
import com.ceiba.parqueadero.repository.VehiculoRepository;
import com.ceiba.parqueadero.service.ParqueaderoService;
import com.ceiba.parqueadero.service.RegistradorSalidaService;
import com.ceiba.parqueadero.service.TiempoService;
import com.ceiba.parqueadero.service.VehiculoService;
import com.ceiba.parqueadero.util.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalidaVehiculosTest {

	private static final String PLACA = "placa";
	
	@Autowired
	ParqueaderoRepository parqueaderoRepository;

	@Autowired
	ParqueaderoService parqueaderoService;

	@Autowired
	RegistradorSalidaService registradorSalidaService;

	@Autowired
	VehiculoRepository vehiculoRepository;

	@Autowired
	ParqueoRepository parqueoRepository;

	@Autowired
	VehiculoService vehiculoService;
	
	@MockBean
	TiempoService tiempoService;
	
	@Before
	public final void antesDeLosTest() {
		vehiculoRepository.save(new Vehiculo(1L,"AAA111","M",500));
		vehiculoRepository.save(new Vehiculo(2L,"BBB222","C",0));
		LocalDateTime fechaIngreso= LocalDateTime.of(2019, 4, 1, 0, 1);
		parqueoRepository.save(new Parqueo(1L,fechaIngreso, null, "AAA111", 1L, 0));
		parqueoRepository.save(new Parqueo(2L,fechaIngreso, null, "BBB222", 1L, 0));
		parqueaderoRepository.save(new Parqueadero(1L, 500, 4000, 2000, 1000, 8000, 9, 19));
	}

	@After
	public final void despuesDeLosTest() {
		vehiculoRepository.deleteAll();
		parqueoRepository.deleteAll();
		parqueaderoRepository.save(new Parqueadero(1L, 500, 4000, 2000, 1000, 8000, 10, 20));
	}

	@Test
	public final void salidaMotoTest() throws JSONException {
		JSONObject parqueoJson = new JSONObject();
		parqueoJson.put(PLACA, " AAA111 ");
		parqueoJson.put("fechaIngreso", null);
		parqueoJson.put("fechaSalida", null);
		parqueoJson.put("parqueadero", null);
		
				
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 4, 14, 0, 1);
		// when
		when(tiempoService.tiempoActualTipoLocalDateTime()).thenReturn(fechaSalida);
		
		Response res = registradorSalidaService.registrarSalida(parqueoJson.toString());
		//then
		assertThat(res.getResponseCode()).isEqualTo(200);
	}

	@Test
	public final void salidaCarroTest() throws JSONException {
		JSONObject parqueoJson = new JSONObject();
		parqueoJson.put(PLACA, " BBB222 ");
		parqueoJson.put("fechaIngreso", null);
		parqueoJson.put("fechaSalida", null);
		parqueoJson.put("parqueadero", null);
		
				
		LocalDateTime fechaSalida = LocalDateTime.of(2019, 4, 14, 0, 1);
		// when
		when(tiempoService.tiempoActualTipoLocalDateTime()).thenReturn(fechaSalida);
		
		Response res = registradorSalidaService.registrarSalida(parqueoJson.toString());
		//then
		assertThat(res.getResponseCode()).isEqualTo(200);
	}
}
