package com.demo.dto;

import com.demo.model.Category;
import com.demo.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchOfferDto {
    private String keyword;
    private Category category;
    private Category conditionCategory;
    private double priceRangeUpper;
    private double priceRangeLower;
    private String city;
    private String district;
    private String SortedBy;

}
