package com.khaled.project.commons.monotoring.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class monthlyMonotoring {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idFacture;
    private Integer projectId;
    private Double realizedJH;
    private Double projectCharge;
    //private Double gainFacture;
}
