package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Parqueo;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.service.ParqueoService;
import com.ceiba.parqueadero.service.imp.VehiculoServiceImp;

public class VehiculoServiceTest {

	private VehiculoServiceImp vehiculoService;
	
	@MockBean
	private ParqueoService parqueoService;
	

	
	
	@Before
	public void setUp() throws Exception{
		
		vehiculoService = new VehiculoServiceImp();
	}
	
	@Test
	public void testConvertirJsonAVehiculo() throws Exception {
		
		
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put("placa", " abc123 ");
		vehiculoJson.put("tipoVehiculo", "M");
		vehiculoJson.put("cilindraje", 1000);
		
		Vehiculo vehiculo =	vehiculoService.convertirYValidarJsonAVehiculo(vehiculoJson.toString());
		
		Vehiculo vehiculoEsperado = new Vehiculo();
		vehiculoEsperado.setPlaca("ABC123");
		vehiculoEsperado.setTipoVehiculo("M");
		vehiculoEsperado.setCilindraje(1000);
		assertThat("ABC123").isEqualTo((vehiculo.getPlaca()));
			
	
	}
	
	///////////////////////////////////VALIDAR VEHICULO DE ENTRADA//////////////////////////////
	
	@Test(expected = Exception.class)
	public void testValidaVehiculoMotoSinCilindraje() throws Exception {
		Vehiculo vehiculo = new Vehiculo(1L,"ABC123","M",0);
		vehiculoService.validarVehiculo(vehiculo);
	}
	
	@Test(expected = Exception.class)
	public void testValidaVehiculoSinPlaca() throws Exception {
		Vehiculo vehiculo = new Vehiculo(1L,null,"M",100);
		vehiculoService.validarVehiculo(vehiculo);
	}
	
	@Test(expected = Exception.class)
	public void testValidaVehiculoSinTipoVehiculo() throws Exception {
		Vehiculo vehiculo = new Vehiculo(1L,"ABC123",null,100);
		vehiculoService.validarVehiculo(vehiculo);
	}
	

	//////////////////////////////////VALIDAR CELDAS DISPONIBLES//////////////////////////////////////
	
	@Test(expected = Exception.class)
	public void testValidarCeldasDisponiblesCeroDeMoto() throws Exception{
		Parqueadero parqueadero = new Parqueadero(1L, 500, 4000, 2000, 1000, 8000, 0, 1);
		Vehiculo vehiculo = new Vehiculo(1L, "RGM20E", "M", 100);
		vehiculoService.validarCeldasDisponibles(parqueadero, vehiculo);
	}
	
	@Test(expected = Exception.class)
	public void testValidarCeldasDisponiblesCeroDeCarro() throws Exception{
		Parqueadero parqueadero = new Parqueadero(1L, 500, 4000, 2000, 1000, 8000, 1, 0);
		Vehiculo vehiculo = new Vehiculo(1L, "RGM20E", "C", 100);
		vehiculoService.validarCeldasDisponibles(parqueadero, vehiculo);
	}
	
	////////////////////////////////////VALIDAR VEHICULO EN PARQUEADERO//////////////////////////////////
	
	@Test(expected = Exception.class)
	public void validarVehiculoEnParqueadero() throws Exception{
		Vehiculo vehiculo = new Vehiculo(1L, "RGM20E", "C", 100);
		Parqueo parqueo = null;
		
		doReturn(parqueo).when(parqueoService).findByPlacaAndFechaSalida("RGM20E", null);
		
		vehiculoService.validarVehiculoEnParqueadero(vehiculo);
		
	}
	
	///////////////////////////////////////VALIDAR PLACA Y DIA DE LA SEMANA//////////////////////////////////////
	
	@Test
	public void testValidarPlacaYDiaSemanaDomingo() throws Exception{
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaSalidaStr = "2019-04-14 00:00:01";//Domingo 14 de abril de 2019
		Date fechaDomingo = formatoFecha.parse(fechaSalidaStr);
		Vehiculo vehiculo = new Vehiculo(1L,"ABC123","M",100);
		
		vehiculoService.validarPlacaYDiaSemana(vehiculo, fechaDomingo);
		
	}
	@Test
	public void testValidarPlacaYDiaSemanaLunes() throws Exception{
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaSalidaStr = "2019-04-15 00:00:01";//Lunes 15 de abril de 2019
		Date fechaDomingo = formatoFecha.parse(fechaSalidaStr);
		Vehiculo vehiculo = new Vehiculo(1L,"ABC123","M",100);
		
		vehiculoService.validarPlacaYDiaSemana(vehiculo, fechaDomingo);
	}
	
	@Test(expected = Exception.class)
	public void testValidarPlacaYDiaSemanaMartes() throws Exception{
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaSalidaStr = "2019-04-16 00:00:01";//Martes 15 de abril de 2019
		Date fechaDomingo = formatoFecha.parse(fechaSalidaStr);
		Vehiculo vehiculo = new Vehiculo(1L,"ABC123","M",100);
		
		vehiculoService.validarPlacaYDiaSemana(vehiculo, fechaDomingo);
	}

}
