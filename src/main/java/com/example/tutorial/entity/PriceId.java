package com.example.tutorial.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PriceId implements Serializable {

    private static final long serialVersionUID = -4741726363717717657L;

    @Column(length = 20)
    private String date;

    @Column(length = 6)
    private String symbol;
}
