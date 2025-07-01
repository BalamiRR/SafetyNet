package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JsonDataConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    /**
     * Adds a new fire station entry if it does not already exist.
     *
     * @param fireStation The FireStation object containing address and station number.
     * @return {@code true} if the fire station was successfully added;
     *         {@code false} if the station already exists or input is invalid.
     */
    public Boolean savingStation(FireStation fireStation){
        boolean addressFound = false;
        if(fireStation.getAddress() == null || fireStation.getStation() == 0){
            return false;
        }
        for(FireStation fireStationA : this.getAllFireStation()){
            if(fireStationA.getAddress().equals(fireStation.getAddress()) && fireStationA.getStation() == fireStation.getStation()){
                addressFound = true;
                break;
            }
        }
        if(!addressFound){
            this.getAllFireStation().add(fireStation);
        }
        return !addressFound;
    }

    /**
     * Updates station number by address.
     *
     * @param address Fire station address.
     * @param fireStation New station info.
     * @return true if updated, false if not found or invalid input.
     */
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

    /**
     * Deletes all fire stations with the given address.
     * @param address Address to match for deletion.
     * @return true if any fire station was removed; false otherwise.
     */
    public Boolean deleteByAddress(String address) {
        return jsonDataConverter.getFireStations()
                .removeIf(fs -> fs.getAddress().equalsIgnoreCase(address));
    }

    /**
     * Deletes all fire stations matching the given station number.
     * @param station Station number to match for deletion (as String).
     * @return true if any fire station was removed; false otherwise.
     */    public Boolean deleteByStation(String station) {
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
        for(FireStation fireStationA :stations){
            address.add(fireStationA.getAddress());
        }
        return address;
    }

    public List<FireStation> findStationByAddress(String address){
        List<FireStation> list = new ArrayList<>();
        for(FireStation fireStation : this.getAllFireStation()){
            if(fireStation.getAddress().equals(address)){
                list.add(fireStation);
            }
        }
        return list;
    }

}
