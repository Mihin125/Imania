package com.demo.dto;

import com.demo.model.Category;
import com.demo.model.WorkingNature;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaveOfferDto {

    private String companyName;
    private String positions;
    private WorkingNature workingNature;
    private Category category;
    private double price;
    private String district;
    private String photo;
    private long user;
    private LocalDateTime postedDate;
    private LocalDateTime expiredDate;
}
