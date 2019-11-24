package com.fixer.sampleproject.rest;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fixer.sampleproject.converter.RoomEntityToReservableRoomResponseConverter;
import com.fixer.sampleproject.entity.ReservationEntity;
import com.fixer.sampleproject.entity.RoomEntity;
import com.fixer.sampleproject.model.request.ReservationRequest;
import com.fixer.sampleproject.model.response.ReservableRoomResponse;
import com.fixer.sampleproject.model.response.ReservationResponse;
import com.fixer.sampleproject.repository.PageableRoomRepository;
import com.fixer.sampleproject.repository.ReservationRepository;
import com.fixer.sampleproject.repository.RoomRepository;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {

	@Autowired
	private PageableRoomRepository pageableRoomRepository;
	@Autowired
	private RoomEntityToReservableRoomResponseConverter converter;	
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private ConversionService conversionService;

	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ReservableRoomResponse> getAvailableRooms(
			@RequestParam(value = "checkin") @DateTimeFormat(iso = ISO.DATE) LocalDate checkin,
			@RequestParam(value = "checkout") @DateTimeFormat(iso = ISO.DATE) LocalDate checkout, Pageable pageable) {

		Page<RoomEntity> roomEntityList = pageableRoomRepository.findAll(pageable);

		return roomEntityList.map(converter::convert);
	}
	
	@RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoomEntity> getRoomById(@PathVariable Long roomId) {
		Optional<RoomEntity> roomEntity = roomRepository.findById(roomId);
		
		if(roomEntity.isPresent()) {
			return new ResponseEntity<>(roomEntity.get(), HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest reservationRequest) {
		ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
		reservationEntity = reservationRepository.save(reservationEntity);
		
		Optional<RoomEntity> roomEntity = roomRepository.findById(reservationRequest.getRoomId());
		if(roomEntity.isPresent()) {
			roomEntity.get().addReservationEntity(reservationEntity);	
			roomRepository.save(roomEntity.get());
			reservationEntity.setRoomEntity(roomEntity.get());
		}
		
		ReservationResponse reservationResponse = conversionService.convert(reservationEntity, ReservationResponse.class);
		System.out.println(reservationResponse.getId());
		
		return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
	}

	@RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservableRoomResponse> updateReservation(@RequestBody ReservationRequest reservationRequest) {
		return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
