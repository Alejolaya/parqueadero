package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDateTime;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.repository.VehiculoRepository;
import com.ceiba.parqueadero.service.ParqueoService;
import com.ceiba.parqueadero.service.VehiculoService;
import com.ceiba.parqueadero.util.ValidarVehiculoException;

public class VehiculoServiceTest {

	private static final String RGM20E = "RGM20E";

	private static final String ABC123 = "ABC123";

	private VehiculoService vehiculoService;

	@MockBean
	private ParqueoService parqueoService;
	@Autowired
	private VehiculoRepository vehiculoRepository;

	@Before
	public void setUp() {

		vehiculoService = new VehiculoService(vehiculoRepository, parqueoService);
	}

	@Test
	public void testConvertirJsonAVehiculo() throws ValidarVehiculoException, JSONException {

		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put("placa", " abc123 ");
		vehiculoJson.put("tipoVehiculo", "M");
		vehiculoJson.put("cilindraje", 1000);

		Vehiculo vehiculo = vehiculoService.convertirYValidarJsonAVehiculo(vehiculoJson.toString());

		Vehiculo vehiculoEsperado = new Vehiculo();
		vehiculoEsperado.setPlaca(ABC123);
		vehiculoEsperado.setTipoVehiculo("M");
		vehiculoEsperado.setCilindraje(1000);
		assertThat(ABC123).isEqualTo((vehiculo.getPlaca()));

	}

	/////////////////////////////////// VALIDAR VEHICULO DE
	/////////////////////////////////// ENTRADA//////////////////////////////

	@Test(expected = Exception.class)
	public void testValidaVehiculoMotoSinCilindraje() throws ValidarVehiculoException {
		Vehiculo vehiculo = new Vehiculo(1L, ABC123, "M", 0);
		vehiculoService.validarVehiculo(vehiculo);
	}

	@Test(expected = Exception.class)
	public void testValidaVehiculoSinPlaca() throws ValidarVehiculoException {
		Vehiculo vehiculo = new Vehiculo(1L, null, "M", 100);
		vehiculoService.validarVehiculo(vehiculo);
	}

	@Test(expected = Exception.class)
	public void testValidaVehiculoSinTipoVehiculo() throws ValidarVehiculoException {
		Vehiculo vehiculo = new Vehiculo(1L, ABC123, null, 100);
		vehiculoService.validarVehiculo(vehiculo);
	}

	////////////////////////////////// VALIDAR CELDAS
	////////////////////////////////// DISPONIBLES//////////////////////////////////////

	@Test(expected = Exception.class)
	public void testValidarCeldasDisponiblesCeroDeMoto() throws ValidarVehiculoException {
		Parqueadero parqueadero = new Parqueadero(1L, 500, 4000, 2000, 1000, 8000, 0, 1);
		Vehiculo vehiculo = new Vehiculo(1L, RGM20E, "M", 100);
		vehiculoService.validarCeldasDisponibles(parqueadero, vehiculo);
	}

	@Test(expected = Exception.class)
	public void testValidarCeldasDisponiblesCeroDeCarro() throws ValidarVehiculoException {
		Parqueadero parqueadero = new Parqueadero(1L, 500, 4000, 2000, 1000, 8000, 1, 0);
		Vehiculo vehiculo = new Vehiculo(1L, RGM20E, "C", 100);
		vehiculoService.validarCeldasDisponibles(parqueadero, vehiculo);
	}

	//////////////////////////////////// VALIDAR VEHICULO EN
	//////////////////////////////////// PARQUEADERO//////////////////////////////////

	@Test(expected = Exception.class)
	public void validarVehiculoEnParqueadero() throws ValidarVehiculoException {
		Vehiculo vehiculo = new Vehiculo(1L, RGM20E, "C", 100);
		Parqueo parqueo = null;

		doReturn(parqueo).when(parqueoService).findByPlacaAndFechaSalida(RGM20E, null);

		vehiculoService.validarVehiculoEnParqueadero(vehiculo);

	}

	/////////////////////////////////////// VALIDAR PLACA Y DIA DE LA
	/////////////////////////////////////// SEMANA//////////////////////////////////////

	@Test
	public void testValidarPlacaYDiaSemanaDomingo() throws ValidarVehiculoException {

		// Domingo 14 de abril de 2019
		LocalDateTime fechaDomingo = LocalDateTime.of(2019, 4, 14, 0, 1);
		Vehiculo vehiculo = new Vehiculo(1L, ABC123, "M", 100);

		vehiculoService.validarPlacaYDiaSemana(vehiculo, fechaDomingo);

	}

	@Test
	public void testValidarPlacaYDiaSemanaLunes() throws ValidarVehiculoException {
		// Lunes 15 de abril de 2019
		LocalDateTime fechaLunes = LocalDateTime.of(2019, 4, 15, 0, 1);
		Vehiculo vehiculo = new Vehiculo(1L, ABC123, "M", 100);

		vehiculoService.validarPlacaYDiaSemana(vehiculo, fechaLunes);
	}

	@Test(expected = Exception.class)
	public void testValidarPlacaYDiaSemanaMartes() throws ValidarVehiculoException {
		// Martes 16 de abril de 2019
		LocalDateTime fechaLunes = LocalDateTime.of(2019, 4, 16, 0, 1);
		Vehiculo vehiculo = new Vehiculo(1L, ABC123, "M", 100);

		vehiculoService.validarPlacaYDiaSemana(vehiculo, fechaLunes);
	}

}
