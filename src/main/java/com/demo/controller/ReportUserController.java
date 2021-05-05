package com.demo.controller;

import com.demo.model.RedList;
import com.demo.service.RedListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("career/red-list")
public class ReportUserController {
    @Autowired
    RedListService redListService;

    @GetMapping("/admin")
    public List<RedList> getAll(){
        return redListService.getAll();
    }

    @DeleteMapping("/admin/delete/{userId}")
    public void deleteRedListUser(@PathVariable long userId){
        redListService.deleteRedListUser(userId);
    }



}
