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
    private String description;
    private Category category;
    private WorkingNature workingNature;
    private String district;
    private String photo;
    private long user;
    private LocalDateTime postedDate;
    private LocalDateTime expirationDate;
}
