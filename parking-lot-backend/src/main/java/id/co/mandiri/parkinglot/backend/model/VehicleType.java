package id.co.mandiri.parkinglot.backend.model;

import id.co.mandiri.parkinglot.common.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "vehicle_type", uniqueConstraints = @UniqueConstraint(columnNames = "type_vehicle"))
public class VehicleType extends EntityBase {

    @Column(name = "type_vehicle")
    private String typeVehicle;

    @Column(name = "price_per_hour")
    private Double pricePerHour;

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
