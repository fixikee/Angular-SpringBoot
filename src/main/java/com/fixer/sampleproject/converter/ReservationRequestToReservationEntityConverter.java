package com.fixer.sampleproject.converter;

import org.springframework.core.convert.converter.Converter;

import com.fixer.sampleproject.entity.ReservationEntity;
import com.fixer.sampleproject.model.request.ReservationRequest;

public class ReservationRequestToReservationEntityConverter implements Converter<ReservationRequest, ReservationEntity> {

	@Override
	public ReservationEntity convert(ReservationRequest source) {
		ReservationEntity reservationEntity = new ReservationEntity();
		reservationEntity.setCheckin(source.getCheckin());
		reservationEntity.setCheckout(source.getCheckout());
		
		if(source.getId() != null) {
			reservationEntity.setId(source.getId());
		}
		
		return reservationEntity;
	}

}
