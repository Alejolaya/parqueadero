package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.service.ParqueoService;
import com.ceiba.parqueadero.service.ParqueoServiceImp;

public class ParqueoServiceTest {

	
	private static final String HORAINGRESO = "2019-01-01 00:00:00";
	private ParqueoService parqueoService;

	
	@Before
	public void setUp() {
		parqueoService = new ParqueoServiceImp();
	}
	
	
	////////////////////////////////////////////PRUEBAS DE CALCULO DE DIAS////////////////////////
	@Test
	public final void testCalcularDiasAPagar() {
		
		Long diasAPagar = parqueoService.calcularDiasAPagar(24L);
		
		assertThat(diasAPagar).isEqualTo(1);
		
	}
	
	@Test
	public final void testCalcularDiasAPagarMenorde24Horas() {
		
		Long diasAPagar = parqueoService.calcularDiasAPagar(23L);
		
		assertThat(diasAPagar).isEqualTo(1);
		
	}

	@Test
	public final void testCalcularDiasAPagarMayorA24Horas() {
		
		Long diasAPagar = parqueoService.calcularDiasAPagar(34L);
		
		assertThat(diasAPagar).isEqualTo(2);
		
	}

	@Test
	public final void testCalcularDiasAPagarMenorde9Horas() {
		
		Long diasAPagar = parqueoService.calcularDiasAPagar(8L);
		
		assertThat(diasAPagar).isEqualTo(0);
		
	}

	@Test
	public final void testCalcularDiasAPagarMayorA9Horas() {
		
		Long diasAPagar = parqueoService.calcularDiasAPagar(10L);
		
		assertThat(diasAPagar).isEqualTo(1);
		
	}
	@Test
	public final void testCalcularDiasAPagarIgualA9Horas() {
		
		Long diasAPagar = parqueoService.calcularDiasAPagar(9L);
		
		assertThat(diasAPagar).isEqualTo(1);
		
	}
	
	//////////////////////////////////////////PRUEBAS DE CALCULOS DE HORAS/////////////////////////////////////////////
	
	@Test
	public final void testCalcularHorasAPagarIgualA9Horas() throws ParseException {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaIngresoStr = HORAINGRESO;
		String fechaSalidaStr = "2019-01-01 09:00:00";
		Date fechaIngreso = formatoFecha.parse(fechaIngresoStr);
		Date fechaSalida = formatoFecha.parse(fechaSalidaStr);
		
		Long diffInMillies = fechaSalida.getTime() - fechaIngreso.getTime();
		Long difEnHoras = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		Long difEnMinutos = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		Long horasAPagar = parqueoService.calcularHorasAPagar(difEnHoras, difEnMinutos);
		
		assertThat(horasAPagar).isEqualTo(0);
		
	}

	@Test
	public final void testCalcularHorasAPagarMenorA9Horas() throws ParseException {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaIngresoStr = HORAINGRESO;
		String fechaSalidaStr = "2019-01-01 07:59:59";
		Date fechaIngreso = formatoFecha.parse(fechaIngresoStr);
		Date fechaSalida = formatoFecha.parse(fechaSalidaStr);
		
		Long diffInMillies = fechaSalida.getTime() - fechaIngreso.getTime();
		Long difEnHoras = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		Long difEnMinutos = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		Long horasAPagar = parqueoService.calcularHorasAPagar(difEnHoras, difEnMinutos);
		
		assertThat(horasAPagar).isEqualTo(8);
		
	}
	@Test
	public final void testCalcularHorasAPagarMayorA9Horas() throws ParseException {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaIngresoStr = HORAINGRESO;
		String fechaSalidaStr = "2019-01-02 07:59:59";
		Date fechaIngreso = formatoFecha.parse(fechaIngresoStr);
		Date fechaSalida = formatoFecha.parse(fechaSalidaStr);
		
		Long diffInMillies = fechaSalida.getTime() - fechaIngreso.getTime();
		Long difEnHoras = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		Long difEnMinutos = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		Long horasAPagar = parqueoService.calcularHorasAPagar(difEnHoras, difEnMinutos);
		
		assertThat(horasAPagar).isEqualTo(8);
		
	}
	
	@Test
	public final void testCalcularHorasAPagarMenorAUnaHora() throws ParseException {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaIngresoStr = HORAINGRESO;
		String fechaSalidaStr = "2019-01-01 00:01:00";
		Date fechaIngreso = formatoFecha.parse(fechaIngresoStr);
		Date fechaSalida = formatoFecha.parse(fechaSalidaStr);
		
		Long diffInMillies = fechaSalida.getTime() - fechaIngreso.getTime();
		Long difEnHoras = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		Long difEnMinutos = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		Long horasAPagar = parqueoService.calcularHorasAPagar(difEnHoras, difEnMinutos);
		
		assertThat(horasAPagar).isEqualTo(1);
		
	}
	
	/////////////////////////////////////////PRUEBA DE TOTAL A PAGAR///////////////////////////////
	@Test
	public final void testCalcularTotalAPagarSiEsMotoBajoCc() {
		Vehiculo vehiculo = new Vehiculo(1L,"ABC123","M",100);
		Parqueadero parqueadero = new Parqueadero(1L,500,4000,2000,1000,8000,10,20);
		Long diasAPagar = 0L;
		Long horasRestantes = 0L;
		
		Long totalAPagar = parqueoService.calcularTotalApagar(vehiculo, parqueadero, diasAPagar, horasRestantes);
		
		
		assertThat(totalAPagar).isEqualTo(0);
		
	}

	@Test
	public final void testCalcularTotalAPagarSiEsMotoAltoCc() {
		Vehiculo vehiculo = new Vehiculo(1L,"ABC123","M",500);
		Parqueadero parqueadero = new Parqueadero(1L,500,4000,2000,1000,8000,10,20);
		Long diasAPagar = 0L;
		Long horasRestantes = 0L;
		
		Long totalAPagar = parqueoService.calcularTotalApagar(vehiculo, parqueadero, diasAPagar, horasRestantes);
		
		
		assertThat(totalAPagar).isEqualTo(2000);
		
	}
	
	@Test
	public final void testCalcularTotalAPagarSiEsCarro() {
		Vehiculo vehiculo = new Vehiculo(1L,"ABC123","C",500);
		Parqueadero parqueadero = new Parqueadero(1L,500,4000,2000,1000,8000,10,20);
		Long diasAPagar = 0L;
		Long horasRestantes = 0L;
		
		Long totalAPagar = parqueoService.calcularTotalApagar(vehiculo, parqueadero, diasAPagar, horasRestantes);
		
		
		assertThat(totalAPagar).isEqualTo(0);
		
	}
	
	
}
