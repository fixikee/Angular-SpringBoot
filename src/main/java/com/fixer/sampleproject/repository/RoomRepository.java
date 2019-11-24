package com.fixer.sampleproject.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.fixer.sampleproject.entity.RoomEntity;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
	
	Optional<RoomEntity> findById(Long id);
}
