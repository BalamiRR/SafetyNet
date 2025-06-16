package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireStationService {

    FireStationRepository fireStationRepository;

    @Autowired
    public FireStationService(FireStationRepository fireStationRepository){
        this.fireStationRepository = fireStationRepository;
    }

    public List<FireStation> getAllFireStations(){
        return fireStationRepository.getAllFireStation();
    }

    public Boolean deleteStation(FireStation fireStation){
        return fireStationRepository.deleteStation(fireStation);
    }

    public Boolean updateFireStation(FireStation fireStation){
        return fireStationRepository.updateFireStation(fireStation);
    }

    public Boolean saveStation(FireStation fireStation){
        return fireStationRepository.savingStation(fireStation);
    }


}
