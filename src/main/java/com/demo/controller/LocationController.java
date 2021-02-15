package com.demo.controller;

import com.demo.dto.DistrictDto;
import com.demo.model.District;
import com.demo.service.DistrictService;
import com.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("94mart/location")
public class LocationController {
    @Autowired
    LocationService locationService;
    @Autowired
    DistrictService districtService;

    public District findDistrictById(@PathVariable long districtId){
        return locationService.findDistrictById(districtId);
    }
    @PostMapping("/admin/district/save")
    public void saveDistrict(@RequestBody DistrictDto districtDto){
        districtService.saveDistrict(districtDto);
    }
}
