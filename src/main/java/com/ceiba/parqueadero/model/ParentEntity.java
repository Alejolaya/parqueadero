package com.ceiba.parqueadero.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.GenerationType;

@MappedSuperclass
@Access(AccessType.FIELD)
public class ParentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	public ParentEntity() {
		super();
	}

	public ParentEntity(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
