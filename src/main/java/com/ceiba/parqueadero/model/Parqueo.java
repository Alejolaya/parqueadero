package com.ceiba.parqueadero.model;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "parqueo")
@Access(AccessType.FIELD)
public class Parqueo extends ParentEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "fecha_ingreso", nullable = false)
	private Date fechaIngreso;
	
	@Column(name = "fecha_salida", nullable = true)
	private Date fechaSalida;
	
	@Column(name = "placa", nullable = false)
	private String placa;
	
	@Column(name = "parqueadero", nullable = true)
	private Long parqueadero;
	
	@Column(name = "costo", nullable = false)
	private int costo;

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getParqueadero() {
		return parqueadero;
	}

	public void setParqueadero(Long parqueadero) {
		this.parqueadero = parqueadero;
	}
	

}
