package id.co.mandiri.parkinglot.backend.model.reporting;


import id.co.mandiri.parkinglot.backend.model.VehicleType;
import id.co.mandiri.parkinglot.common.EntityBase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicle_daily")
public class VehicleDailyReport extends EntityBase {

    @Column(name = "report_date")
    private Date reportDate;

    @Column(name = "sum_total_price")
    private Double sumTotalPrice;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Double getSumTotalPrice() {
        return sumTotalPrice;
    }

    public void setSumTotalPrice(Double sumTotalPrice) {
        this.sumTotalPrice = sumTotalPrice;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
