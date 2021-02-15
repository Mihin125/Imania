package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;


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
    private String position;
    private String companyName;
    private  WorkingNature workingNature;
    private Category category;
    @ManyToOne
    private User user;
    private String photo;
    private LocalDateTime postedDate;
    private LocalDateTime expiredDate;
    private OfferStatus offerStatus;
    @ManyToOne
    private District district;
    @JsonIgnore
    @OneToMany(mappedBy = "offer")
    private List<ReportOffer> reports;

    public Offer(long id, String position, String companyName, WorkingNature workingNature, Category category, User user, String photo, LocalDateTime expiredDate, LocalDateTime postedDate, OfferStatus offerStatus, District district, List<ReportOffer> reports) {
        this.id = id;
        this.companyName = companyName;
        this.position=position;
        this.workingNature = workingNature;
        this.category = category;
        this.user = user;
        this.photo = photo;
        this.postedDate = postedDate;
        this.offerStatus = offerStatus;
        this.district = district;
        this.expiredDate =expiredDate;
        this.reports = reports;
        List<Offer> offerList = user.getOffers();
        offerList.add(this);
        user.setOffers(offerList);
    }


    public Offer() {
    }
}
