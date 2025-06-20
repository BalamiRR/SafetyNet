package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JsonDataConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Repository
public class FireStationRepository {
    private final JsonDataConverter jsonDataConverter;

    public List<FireStation> getAllFireStation(){
        return jsonDataConverter.getFireStations();
    }

    public Boolean savingStation(FireStation fireStation){
        boolean addressFound = false;
        if(fireStation.getAddress() == null || fireStation.getStation() == 0){
            return false;
        }
        for(FireStation fireStationA : this.getAllFireStation()){
            if(fireStationA.getAddress().equals(fireStation.getAddress()) && fireStationA.getStation() == fireStationA.getStation()){
                addressFound = true;
                break;
            }
        }
        if(!addressFound){
            this.getAllFireStation().add(fireStation);
        }
        return !addressFound;
    }

    //http://localhost:8080/fireStation?address=1509 Culver St
    public Boolean updateFireStation(String address, FireStation fireStation){
        if(address == null || fireStation.getStation() == 0){
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

    //DELETE ADDRESS http://localhost:8080/fireStation?address=29 15th St
    //DELETE ADDRESS http://localhost:8080/firestation?address=1509%20Culver%20St&station=3
    public Boolean deleteByAddress(String address) {
        return jsonDataConverter.getFireStations()
                .removeIf(fs -> fs.getAddress().equalsIgnoreCase(address));
    }

    //DELETE STATION http://localhost:8080/fireStation?station=5
    public Boolean deleteByStation(String station) {
        return jsonDataConverter.getFireStations()
                .removeIf(fs -> String.valueOf(fs.getStation()).equalsIgnoreCase(station));
    }

    public Set<FireStation> getStationsByNumber(int stationNumber){
        Set<FireStation> set = new HashSet<>();
        for(FireStation fireStationA : this.getAllFireStation()){
            if(fireStationA.getStation()== stationNumber){
                set.add(fireStationA);
            }
        }
        return set;
    }

    public Set<String> findAllAddresses(Set<FireStation> stations){
        Set<String> address = new HashSet<>();
        for(FireStation fireStationA : this.getAllFireStation()){
            address.add(fireStationA.getAddress());
        }
        return address;
    }
}
