package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JsonDataConverter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationRepository {
    private final JsonDataConverter jsonDataConverter;

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

    public Boolean updateFireStation(String address, FireStation fireStation){
        if(address == null || fireStation.getStation() == null){
            return false;
        }
        for(FireStation fireStationA : this.getAllFireStation()){
            if(fireStationA.getAddress().equals(address)){
                fireStationA.setStation(fireStation.getStation());
                return true;
            }
        }
        return false;
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

    //DELETE ADDRESS http://localhost:8080/fireStation?address=29 15th St
    public Boolean deleteByAddress(String address) {
        return jsonDataConverter.getFireStations()
                .removeIf(fs -> fs.getAddress().equalsIgnoreCase(address));
    }

    //DELETE STATION http://localhost:8080/fireStation?station=5
    public Boolean deleteByStation(String station) {
        return jsonDataConverter.getFireStations()
                .removeIf(fs -> fs.getStation().equalsIgnoreCase(station));
    }
}
