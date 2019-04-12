package com.ceiba.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.ceiba.parqueadero.model.Vehiculo;
import com.ceiba.parqueadero.service.VehiculoServiceImp;

public class vehiculoServiceTest {

	private VehiculoServiceImp subject;
	@Before
	public void setUp() throws Exception{
		subject = new VehiculoServiceImp();
	}
	
	@Test
	public void testConvertirJsonAVehiculo() throws Exception {
		
		
		JSONObject vehiculoJson = new JSONObject();
		vehiculoJson.put("placa", " abc123 ");
		vehiculoJson.put("tipoVehiculo", "M");
		vehiculoJson.put("cilindraje", 1000);
		
		Vehiculo vehiculo =	subject.convertirJsonAVehiculo(vehiculoJson.toString());
		
		Vehiculo vehiculoEsperado = new Vehiculo();
		vehiculoEsperado.setPlaca("ABC123");
		vehiculoEsperado.setTipoVehiculo("M");
		vehiculoEsperado.setCilindraje(1000);
		assertThat("ABC123").isEqualTo((vehiculo.getPlaca()));
			
	
	}

}
