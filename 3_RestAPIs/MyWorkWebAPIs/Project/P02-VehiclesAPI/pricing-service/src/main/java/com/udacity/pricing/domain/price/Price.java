package com.udacity.pricing.domain.price;

import javax.persistence.*;

import java.math.BigDecimal;

/**
 * Represents the price of a given vehicle, including currency.
 */
/*[KO] Should it implemented as @Entity */
@Entity
public class Price {
    @Id
    private Long vehicleId;
    private String currency;
    private BigDecimal price;

    public Price() {
    }

    public Price(Long vehicleId, String currency, BigDecimal price) {
        this.currency = currency;
        this.price = price;
        this.vehicleId = vehicleId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
