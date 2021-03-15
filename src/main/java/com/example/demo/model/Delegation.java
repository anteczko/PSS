package com.example.demo.model;

import com.example.demo.model.enums.AutoCapacity;
import com.example.demo.model.enums.TransportType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="delegations")
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int delegationId;
    private String description;

    public Delegation(Timestamp dateTimeStart, Timestamp dateTimeStop) {
        this.dateTimeStart = dateTimeStart;
        this.dateTimeStop = dateTimeStop;
    }

    public Delegation(Timestamp dateTimeStart, Timestamp dateTimeStop, User user) {
        this.dateTimeStart = dateTimeStart;
        this.dateTimeStop = dateTimeStop;
        this.user = user;
    }

    @NotNull
    private Timestamp dateTimeStart;
    @NotNull
    private Timestamp dateTimeStop;
    private double travelDietAmount =30;
    private int breakfastNumber =0;
    private int dinnerNumber =0;
    private int supperNumber =0;
    private TransportType transportType;
    private double ticketPrice;
    private AutoCapacity autoCapacity;
    private double km;
    private String accomodationPrice;
    private double otherTicketsPrice;
    private String otherOutlayDesc;
    private double otherOutlayPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
