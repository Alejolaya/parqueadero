package com.ceiba.parqueadero;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.ceiba.parqueadero.dao.ParqueaderoRepository;
import com.ceiba.parqueadero.dao.ParqueoRepository;
import com.ceiba.parqueadero.model.Parqueadero;
import com.ceiba.parqueadero.service.ParqueoService;
import com.ceiba.parqueadero.service.ParqueoServiceImp;

public class ParqueoServiceTest {

//	static class ParqueoServiceTestContextConfiguration{
//		@Bean
//		public ParqueoService parqueoService() {
//			return new ParqueoServiceImp();
//		}
//	}
//	
//	
//	@Autowired
//	private ParqueoService parqueoService;
//	
//	@MockBean
//	private ParqueoRepository parqueoRepository;
//	private ParqueaderoRepository parqueaderoRepository;
//	
//	@Before
//	public void setUp() {
//		Parqueadero parqueadero = new Parqueadero(1L, 500, 4000, 2000, 2000, 8000, 10, 20);
//		Mockito.when(parqueaderoRepository.findOne(1L)).thenReturn(parqueadero);
//	}
//	@Test
//	public final void testIngresar() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testPagar() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testFindByPlacaAndFechaSalida() {
//		fail("Not yet implemented"); // TODO
//	}

}
