package com.ceiba.parqueadero.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "parqueaderos")
@Access(AccessType.FIELD)
public class Parqueadero extends ParentEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "moto_hora", nullable = false)
	private int motoHora;
	
	@Column(name = "moto_dia", nullable = false)
	private int motoDia;
	
	@Column(name = "alto_cc", nullable = false)
	private int altoCc;
	
	
	@Column(name = "carro_hora", nullable = false)
	private int carroHora;
	
	@Column(name = "carro_dia", nullable = false)
	private int carroDia;
	
	@Column(name = "celdas_moto",nullable = false)
	private int celdasMoto;
	
	@Column(name = "celdas_carro", nullable = false)
	private int celdasCarro;

	public int getMotoHora() {
		return motoHora;
	}

	public void setMotoHora(int motoHora) {
		this.motoHora = motoHora;
	}

	public int getMotoDia() {
		return motoDia;
	}

	public void setMotoDia(int motoDia) {
		this.motoDia = motoDia;
	}

	public int getAltoCc() {
		return altoCc;
	}

	public void setAltoCc(int altoCc) {
		this.altoCc = altoCc;
	}

	public int getCarroHora() {
		return carroHora;
	}

	public void setCarroHora(int carroHora) {
		this.carroHora = carroHora;
	}

	public int getCarroDia() {
		return carroDia;
	}

	public void setCarroDia(int carroDia) {
		this.carroDia = carroDia;
	}

	public int getCeldasMoto() {
		return celdasMoto;
	}

	public void setCeldasMoto(int celdasMoto) {
		this.celdasMoto = celdasMoto;
	}

	public int getCeldasCarro() {
		return celdasCarro;
	}

	public void setCeldasCarro(int celdasCarro) {
		this.celdasCarro = celdasCarro;
	}
	
	
	
	
}
