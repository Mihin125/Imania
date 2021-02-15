package com.demo.service;
import com.demo.model.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
//    @Autowired
//    LocationRepository locationRepository;
    @Autowired
    DistrictService districtService;
    
    public District findDistrictById(long id){
        return districtService.findDistrictById(id);
    }
}
