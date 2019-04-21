package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.repository.ParqueaderoRepository;
import com.ceiba.parqueadero.repository.ParqueoRepository;
import com.ceiba.parqueadero.repository.VehiculoRepository;
import com.ceiba.parqueadero.service.ParqueaderoService;
import com.ceiba.parqueadero.service.RegistradorIngresoService;
import com.ceiba.parqueadero.service.VehiculoService;
import com.ceiba.parqueadero.util.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CeldasDeParqueoTest {
	private static final String CILINDRAJE = "cilindraje";

	private static final String TIPO_VEHICULO = "tipoVehiculo";

	private static final String PLACA = "placa";

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

	@Before
	public final void antesDeLosTest() {
		parqueaderoRepository.save(new Parqueadero(1L, 500, 4000, 2000, 1000, 8000, 0, 0));
	}

	@After
	public final void despuesDeLosTest() {
		vehiculoRepository.deleteAll();
		parqueoRepository.deleteAll();
		parqueaderoRepository.save(new Parqueadero(1L, 500, 4000, 2000, 1000, 8000, 10, 20));
	}

	@Test
	public final void debeFallarAlIngresarMotoTest() throws JSONException {
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put(PLACA, " aaa111 ");
		vehiculoJson.put(TIPO_VEHICULO, "M");
		vehiculoJson.put(CILINDRAJE, 1000);
		
		Response res = registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		
		assertThat(res.getResponseCode()).isEqualTo(406);

	}
	@Test
	public final void debeFallarAlIngresarCarroTest() throws JSONException {
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put(PLACA, " aaa111 ");
		vehiculoJson.put(TIPO_VEHICULO, "C");
		vehiculoJson.put(CILINDRAJE, 0);
		
		Response res = registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		
		assertThat(res.getResponseCode()).isEqualTo(406);

	}
}
