package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
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
import com.ceiba.parqueadero.service.RegistradorIngresoService;
import com.ceiba.parqueadero.service.TiempoService;
import com.ceiba.parqueadero.service.VehiculoService;
import com.ceiba.parqueadero.util.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IngresarVehiculosPlacasConATest {

	private static final String CILINDRAJE = "cilindraje";

	private static final String TIPO_VEHICULO = "tipoVehiculo";

	private static final String PLACA = "placa";

	private static final String AAA222 = "AAA222";


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

	@MockBean
	TiempoService tiempoService;

	@After
	public final void despuesDeLosTest() {
		vehiculoRepository.deleteAll();
		parqueoRepository.deleteAll();
		parqueaderoRepository.save(new Parqueadero(1L, 500, 4000, 2000, 1000, 8000, 10, 20));
	}
	/////////////////////////// ACEPTAR PLACAS QUE COMIENCEN CON
	/////////////////////////// A////////////////////////////////

	@Test
	public final void acepatarMotoConPlacaATest() throws JSONException {
		// given
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put(PLACA, " aaa111 ");
		vehiculoJson.put(TIPO_VEHICULO, "M");
		vehiculoJson.put(CILINDRAJE, 1000);
		// DOMINGO 14 de abril de 2019
		LocalDateTime fechaDomingo = LocalDateTime.of(2019, 4, 14, 0, 1);

		// when
		when(tiempoService.tiempoActualTipoLocalDateTime()).thenReturn(fechaDomingo);

		Response res = registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		// then

		assertThat(res.getResponseCode()).isEqualTo(200);

	}

	@Test
	public final void ingresoCarroPlacaAIntegracionTest() throws JSONException {
		// given
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put(PLACA, " AAA222 ");
		vehiculoJson.put(TIPO_VEHICULO, "C");
		vehiculoJson.put(CILINDRAJE, 0);

		// lunes 15 de abril de 2019

		LocalDateTime fechaLunes = LocalDateTime.of(2019, 4, 15, 0, 1);
		// when
		when(tiempoService.tiempoActualTipoLocalDateTime()).thenReturn(fechaLunes);
		registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		// then
		Vehiculo vehiculo = vehiculoRepository.findByPlaca(AAA222);
		Parqueo parqueo = parqueoRepository.findByPlacaAndFechaSalida(AAA222, null);
		Parqueadero parqueadero = parqueaderoRepository.findOne(1L);

		assertThat(vehiculo.getTipoVehiculo()).isEqualTo("C");
		assertThat(parqueo.getPlaca()).isEqualTo(AAA222);
		assertThat(parqueadero.getCeldasCarro()).isEqualTo(19);

	}

///////////////////////////// RECHAZAR PLACAS QUE COMIENCEN CON A////////////////////////////////

	@Test
	public final void rechazarMotoPlacaAIntegracionTest() throws JSONException {
		// given
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put(PLACA, " AAA333 ");
		vehiculoJson.put(TIPO_VEHICULO, "M");
		vehiculoJson.put(CILINDRAJE, 1000);

		// martes 16 de abril de 2019

		LocalDateTime fechaMartes = LocalDateTime.of(2019, 4, 16, 0, 1);
		// when
		when(tiempoService.tiempoActualTipoLocalDateTime()).thenReturn(fechaMartes);
		Response res = registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		// then

		assertThat(res.getResponseCode()).isEqualTo(406);

	}

	@Test
	public final void rechazarCarroPlacaAIntegracionTest() throws JSONException {
		// given
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put(PLACA, " aaa444 ");
		vehiculoJson.put(TIPO_VEHICULO, "C");
		vehiculoJson.put(CILINDRAJE, 0);

		// miercoles 17 de abril de 2019

		LocalDateTime fechaMiercoles = LocalDateTime.of(2019, 4, 17, 0, 1);
		// when
		when(tiempoService.tiempoActualTipoLocalDateTime()).thenReturn(fechaMiercoles);
		Response res = registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		// then
		assertThat(res.getResponseCode()).isEqualTo(406);

	}
}
