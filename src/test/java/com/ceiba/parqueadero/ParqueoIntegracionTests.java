package com.ceiba.parqueadero;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ceiba.parqueadero.dao.ParqueaderoRepository;
import com.ceiba.parqueadero.model.Parqueadero;

@DataJpaTest
public class ParqueoIntegracionTests {

	@Autowired
	private ParqueaderoRepository parqueaderoRepository;
	
	@Before
	public void iniciarTest() throws Exception {
		Parqueadero parqueadero = new Parqueadero(1L, 500, 4000, 2000, 1000, 8000, 10, 20);
		parqueaderoRepository.save(parqueadero);
	}
	
	@After
	public void finalizarTest() throws Exception{
		
	}
	///////////////////////Registrar motos y carros
	@Test
	public final void test() {
			
	}

}
