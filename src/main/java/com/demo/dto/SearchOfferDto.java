package com.demo.dto;

import com.demo.model.Category;
import com.demo.model.WorkingNature;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchOfferDto {
    private String keyword;
    private String companyName;
    private WorkingNature workingNature;
    private Category category;
    private String district;
    private String sortedBy;
}
