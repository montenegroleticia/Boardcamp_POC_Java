package com.boardcamp.api.models;

import java.time.LocalDate;

import com.boardcamp.api.dtos.RentalsDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalsModel {
    
    public Float calculateOriginalPrice(Integer daysRented, Float pricePerDay) {
        return daysRented * pricePerDay;
    }

    public RentalsModel(RentalsDTO dto, GamesModel game, CustomersModel customer){
        this.customer = customer;
        this.daysRented = dto.getDaysRented();
        this.delayFee = 0;
        this.game = game;
        this.originalPrice = calculateOriginalPrice(dto.getDaysRented(), game.getPricePerDay());
        this.rentDate = LocalDate.now().toString();
        this.returnDate = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String rentDate;

    @Column(nullable = false)
    private Integer daysRented;

    @Column(nullable = true)
    private String returnDate;

    @Column(nullable = false)
    private Float originalPrice;

    @Column(nullable = false)
    private Integer delayFee;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomersModel customer;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private GamesModel game;

}
