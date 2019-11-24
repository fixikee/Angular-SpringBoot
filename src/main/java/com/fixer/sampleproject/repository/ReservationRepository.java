package com.fixer.sampleproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.fixer.sampleproject.entity.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long>{

	
}
