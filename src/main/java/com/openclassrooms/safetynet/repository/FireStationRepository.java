package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JsonDataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationRepository {
    JsonDataConverter jsonDataConverter;

    @Autowired
    public FireStationRepository(JsonDataConverter jsonDataConverter){
        this.jsonDataConverter = jsonDataConverter;
    }

    public List<FireStation> getAllFireStation(){
        return jsonDataConverter.getFireStations();
    }

    public Boolean savingStation(FireStation fireStation){
        boolean addressFound = false;
        if(fireStation.getAddress() == null || fireStation.getStation() == null){
            return false;
        }
        for(FireStation fireStationA : this.getAllFireStation()){
            if(fireStation.getAddress().equals(fireStationA.getAddress()) && fireStationA.getStation() == fireStation.getAddress()){
                addressFound = true;
                break;
            }
        }
        if(!addressFound){
            this.getAllFireStation().add(fireStation);
        }
        return !addressFound;
    }

    public Boolean deleteStation(FireStation fireStation){
        if(fireStation.getStation() != null && fireStation.getAddress() != null){
            for(FireStation fireStationA : this.getAllFireStation()){
                if(fireStation.getAddress().equals(fireStationA.getAddress())){
                    this.getAllFireStation().remove(fireStationA);
                    return true;
                }
            }
        }
        return false;
    }


}
