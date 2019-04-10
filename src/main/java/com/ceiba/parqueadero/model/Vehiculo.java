package com.ceiba.parqueadero.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "vehiculos")
@Access(AccessType.FIELD)
public class Vehiculo extends ParentEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "placa",nullable = false, length = 10)
	private String placa;
	
	@Column(name = "tipo_vehiculo",nullable = false, length = 4)
	private String tipoVehiculo;
	
	@Column(name = "cilindraje",nullable = true, length = 4)
	private int cilindraje;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}


	

}
