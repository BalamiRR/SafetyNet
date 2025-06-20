package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final SearchRepository searchRepository;

    public LinkedHashSet<String> getEmailsByCity(String city){
        return searchRepository.getEmailsByCity(city);
    }

    public LinkedHashSet<String> getAllPhoneNumbers(int fireStationNumber){
        return searchRepository.getAllPhoneNumbers(fireStationNumber);
    }
}
