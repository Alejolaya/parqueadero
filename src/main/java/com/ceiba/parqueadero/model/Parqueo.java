package com.ceiba.parqueadero.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "parqueo")
@Access(AccessType.FIELD)
public class Parqueo extends ParentEntity {



	private static final long serialVersionUID = 1L;
	
	@Column(name = "fecha_ingreso", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;
	
	@Column(name = "fecha_salida", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSalida;
	
	@Column(name = "placa", nullable = false)
	private String placa;
	
	@Column(name = "parqueadero", nullable = true)
	private Long parqueadero;
	
	@Column(name = "costo", nullable = true)
	private int costo;

	public Parqueo(Long id, Date fechaIngreso, Date fechaSalida, String placa, Long parqueadero, int costo) {
		super(id);
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.placa = placa;
		this.parqueadero = parqueadero;
		this.costo = costo;
	}
	
	public Parqueo() {
		
	}
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
