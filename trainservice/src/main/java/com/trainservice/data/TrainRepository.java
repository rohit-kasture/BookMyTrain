package com.trainservice.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TrainRepository extends CrudRepository<TrainEntity, Long> {

	List<TrainEntity> findBySourceAndDestinationAndDateStartingWith(String source, String destination,
			String date);
}
