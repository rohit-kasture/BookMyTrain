package com.trainservice.serviceImpl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainservice.data.TrainEntity;
import com.trainservice.data.TrainRepository;
import com.trainservice.model.CreateTrainRequestModel;
import com.trainservice.model.CreateTrainResponseModel;
import com.trainservice.model.TrainRequestModel;
import com.trainservice.model.TrainResponseModel;

@Service
public class TrainServiceImpl {
	@Autowired
	TrainRepository trainRepository;

	public CreateTrainResponseModel save(CreateTrainRequestModel createTrainRequestModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		TrainEntity userEntity = modelMapper.map(createTrainRequestModel, TrainEntity.class);
		trainRepository.save(userEntity);
		return modelMapper.map(userEntity, CreateTrainResponseModel.class);
	}

	public List<CreateTrainResponseModel> getallTrains() {
		Iterable<TrainEntity> foundTrainlist = trainRepository.findAll();
		List<TrainEntity> foundTrainLlist = new ArrayList<TrainEntity>();
		for (TrainEntity lis : foundTrainlist) {
			foundTrainLlist.add(lis);
		}

		List<CreateTrainResponseModel> trainlist = new ArrayList<CreateTrainResponseModel>();
		if (foundTrainLlist == null || foundTrainLlist.isEmpty()) {
			return trainlist;
		}

		Type listType = new TypeToken<List<CreateTrainResponseModel>>() {
		}.getType();

		trainlist = new ModelMapper().map(foundTrainlist, listType);
		return trainlist;
	}

	public List<TrainResponseModel> searchTrain(TrainRequestModel trainRequestModel) {
		List<TrainEntity> foundTrains = trainRepository.findBySourceAndDestinationAndDateStartingWith(
				trainRequestModel.getSource(), trainRequestModel.getDestination(), trainRequestModel.getDate());
		
		List<TrainResponseModel> trainlist = new ArrayList<TrainResponseModel>();
		if (foundTrains == null || foundTrains.isEmpty()) {
			return trainlist;
		}

		Type listType = new TypeToken<List<CreateTrainResponseModel>>() {
		}.getType();

		trainlist = new ModelMapper().map(foundTrains, listType);
		return trainlist;
	}
}
