package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Offer {
    @Id
    @GeneratedValue
    private long id;
    private String companyName;
    private String description;
    private Category category;
    private WorkingNature workingNature;
    @ManyToOne
    private User user;
    private String photo;
    private int viewCount;
    private LocalDateTime postedDate;
    private LocalDateTime expirationDate;
    private OfferStatus offerStatus;
    @ManyToOne
    private District district;
    @JsonIgnore
    @OneToMany(mappedBy = "offer")
    private List<ReportOffer> reports;

    public Offer(long id, String companyName, String description,LocalDateTime expirationDate, Category category, WorkingNature workingNature, User user, String photo, int viewCount, LocalDateTime postedDate, OfferStatus offerStatus, District district, List<ReportOffer> reports) {
        this.id = id;
        this.companyName = companyName;
        this.description = description;
        this.category = category;
        this.workingNature = workingNature;
        this.user = user;
        this.photo = photo;
        this.viewCount = viewCount;
        this.postedDate = postedDate;
        this.offerStatus = offerStatus;
        this.district = district;
        this.reports = reports;
        this.expirationDate = expirationDate;
        List<Offer> offerList = user.getOffers();
        offerList.add(this);
        user.setOffers(offerList);
    }
    public Offer() {
    }
}
