package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.ceiba.parqueadero.service.RegistrarIngresoService;
import com.ceiba.parqueadero.service.TiempoService;
import com.ceiba.parqueadero.service.VehiculoService;
import com.ceiba.parqueadero.util.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IngresarVehiculosPlacasConA {


	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	private static final String CILINDRAJE = "cilindraje";

	private static final String TIPO_VEHICULO = "tipoVehiculo";

	private static final String PLACA = "placa";

	private static final String AAA222 = "AAA222";

	private static final String AAA111 = "AAA111";

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

//	@MockBean
//	TiempoService tiempoService;

//	@Before
//	public final void antesDeLosTest() {
//		vehiculoRepository.deleteAll();
//		parqueoRepository.deleteAll();
//		parqueaderoRepository.save(new Parqueadero(1L,500,4000,2000,1000,8000,10,20));
//	}
//	
	@After
	public final void despuesDeLosTest() {
		vehiculoRepository.deleteAll();
		parqueoRepository.deleteAll();
		parqueaderoRepository.save(new Parqueadero(1L,500,4000,2000,1000,8000,10,20));
	}
	/////////////////////////// ACEPTAR PLACAS QUE COMIENCEN CON A////////////////////////////////

//	@Test
//	public final void ingresoMotoPlacaAIntegracionTest() throws JSONException, ParseException {
//		// given
//		JSONObject vehiculoJson = new JSONObject();
//		vehiculoJson.put(PLACA, " aaa111 ");
//		vehiculoJson.put(TIPO_VEHICULO, "M");
//		vehiculoJson.put(CILINDRAJE, 1000);
//		SimpleDateFormat formatoFecha = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
//		String fechaSalidaStr = "2019-04-14 00:00:01";// Domingo 14 de abril de 2019
//		Date fechaDomingo = formatoFecha.parse(fechaSalidaStr);
//
//		// when
//		doReturn(fechaDomingo).when(tiempoService).tiempoActualTipoDate();
//		Response res = registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
//		// then
//		
//		assertThat(res.getResponseCode()).isEqualTo(200);


//	}
	
//	@Test
//	public final void ingresoCarroPlacaAIntegracionTest() throws JSONException, ParseException {
//		// given
//		JSONObject vehiculoJson = new JSONObject();
//		vehiculoJson.put(PLACA, " AAA222 ");
//		vehiculoJson.put(TIPO_VEHICULO, "C");
//		vehiculoJson.put(CILINDRAJE, 0);
//		SimpleDateFormat formatoFecha = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
//		String fechaSalidaStr = "2019-04-15 00:00:01";// lunes 15 de abril de 2019
//		Date fechaLunes = formatoFecha.parse(fechaSalidaStr);
//
//		// when
//		doReturn(fechaLunes).when(tiempoService).tiempoActualTipoDate();
//		registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
//		// then
//		Vehiculo vehiculo = vehiculoRepository.findByPlaca(AAA222);
//		Parqueo parqueo = parqueoRepository.findByPlacaAndFechaSalida(AAA222, null);
//		Parqueadero parqueadero = parqueaderoRepository.findOne(1L);
//
//		assertThat(vehiculo.getTipoVehiculo()).isEqualTo("C");
//		assertThat(parqueo.getPlaca()).isEqualTo(AAA222);
//		assertThat(parqueadero.getCeldasCarro()).isEqualTo(19);
//		
//
//
//	}
//	
///////////////////////////// RECHAZAR PLACAS QUE COMIENCEN CON A////////////////////////////////
	
	@Test
	public final void rechazarMotoPlacaAIntegracionTest() throws JSONException, ParseException {
		// given
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put(PLACA, " AAA333 ");
		vehiculoJson.put(TIPO_VEHICULO, "M");
		vehiculoJson.put(CILINDRAJE, 1000);
		SimpleDateFormat formatoFecha = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		String fechaSalidaStr = "2019-04-16 00:00:01";// martes 16 de abril de 2019
		Date fechaMartes = formatoFecha.parse(fechaSalidaStr);

		// when
		//doReturn(fechaMartes).when(tiempoService).tiempoActualTipoDate();
		Response res =registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		// then
		
		assertThat(res.getResponseCode()).isEqualTo(406);

	}
	
	@Test
	public final void rechazarCarroPlacaAIntegracionTest() throws JSONException, ParseException {
		// given
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put(PLACA, " aaa444 ");
		vehiculoJson.put(TIPO_VEHICULO, "C");
		vehiculoJson.put(CILINDRAJE, 0);
		SimpleDateFormat formatoFecha = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		String fechaSalidaStr = "2019-04-17 00:00:01";// miercoles 17 de abril de 2019
		Date fechaMiercoles = formatoFecha.parse(fechaSalidaStr);

		// when
		//doReturn(fechaMiercoles).when(tiempoService).tiempoActualTipoDate();
		Response res =registrarIngresoService.registrarIngresoVehiculo(vehiculoJson.toString());
		// then
		assertThat(res.getResponseCode()).isEqualTo(406);

	}
}
