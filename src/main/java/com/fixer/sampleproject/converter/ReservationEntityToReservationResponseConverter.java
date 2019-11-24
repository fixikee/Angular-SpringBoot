package com.fixer.sampleproject.converter;

import org.springframework.core.convert.converter.Converter;

import com.fixer.sampleproject.entity.ReservationEntity;
import com.fixer.sampleproject.model.response.ReservationResponse;

public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse> {

	@Override
	public ReservationResponse convert(ReservationEntity source) {
		ReservationResponse reservationResponse = new ReservationResponse();
		reservationResponse.setId(source.getId());
		reservationResponse.setCheckin(source.getCheckin());
		reservationResponse.setCheckout(source.getCheckout());
		
		return reservationResponse;
	}

}
