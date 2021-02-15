package com.demo.service;

import com.demo.dto.DistrictDto;
import com.demo.model.District;
import com.demo.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictService {
    @Autowired
    DistrictRepository districtRepository;

    public District findDistrictById(long id){
        return districtRepository.findById(id).orElseThrow(NullPointerException::new);
    }
    public District findDistrictByDistrictName(String districtName){
        return districtRepository.findDistinctByDistrictName(districtName);
    }

    public void saveDistrict(DistrictDto districtDto){
        District district = new District();
        district.setDistrictName(districtDto.getDistrictName());
//        List<City> cities = districtDto.getCities().stream().map(x->cityService.findCityById(x)).collect(Collectors.toList());
//        district.setCities(cities);
        districtRepository.save(district);
    }

}
