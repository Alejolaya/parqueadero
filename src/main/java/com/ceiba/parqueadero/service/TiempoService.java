package com.ceiba.parqueadero.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TiempoService {

	public Date tiempoActualTipoDate() {

		return new Date();

	}
	
	public LocalDateTime tiempoActualTipoLocalDateTime() {

		return LocalDateTime.now();

	}

	public LocalDateTime convertToLocalDate(Date date) {
		return date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
	}
	
	public Date converToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
