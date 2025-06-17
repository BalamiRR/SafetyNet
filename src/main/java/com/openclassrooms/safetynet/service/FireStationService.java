package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireStationService {
    private final FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository){
        this.fireStationRepository = fireStationRepository;
    }

    public List<FireStation> getAllFireStations(){
        return fireStationRepository.getAllFireStation();
    }

    public Boolean updateFireStation(String address, FireStation fireStation){
        return fireStationRepository.updateFireStation(address, fireStation);
    }

    public Boolean deleteByAddress(String address) {
        return fireStationRepository.deleteByAddress(address);
    }

    public Boolean deleteByStation(String station) {
        return fireStationRepository.deleteByStation(station);
    }

    public Boolean saveStation(FireStation fireStation){
        return fireStationRepository.savingStation(fireStation);
    }


}
