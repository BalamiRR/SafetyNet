package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.FireStation;
import org.springframework.data.repository.CrudRepository;

public interface FireStationRepository extends CrudRepository<FireStation, Long> {
}
