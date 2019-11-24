package com.fixer.sampleproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fixer.sampleproject.entity.RoomEntity;
import com.fixer.sampleproject.repository.RoomRepository;

@Component
public class H2Bootstrap implements CommandLineRunner {

	@Autowired
	RoomRepository roomRepository;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Bootstrapping data: ");
		
		roomRepository.save(new RoomEntity(405, "200"));
		roomRepository.save(new RoomEntity(406, "220"));
		roomRepository.save(new RoomEntity(407, "250"));
		
		Iterable<RoomEntity> itr = roomRepository.findAll();
		
		System.out.println("Printing out data: ");
		for (RoomEntity room : itr) {
			System.out.println(room.getRoomNumber());
		}
	}

}
