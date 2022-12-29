package id.co.mandiri.parkinglot.service.wrapper;

import java.util.Date;

public class VehicleDailyReportWrapper {
    private Long id;
    private Long vehicleTypeId;
    private Date reportDate;
    private String vehicleTypeName;
    private Double pricePerType;
    private Double sumTotalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Double getPricePerType() {
        return pricePerType;
    }

    public void setPricePerType(Double pricePerType) {
        this.pricePerType = pricePerType;
    }

    public Double getSumTotalPrice() {
        return sumTotalPrice;
    }

    public void setSumTotalPrice(Double sumTotalPrice) {
        this.sumTotalPrice = sumTotalPrice;
    }

    @Override
    public String toString() {
        return "VehicleDailyReportWrapper{" +
                "id=" + id +
                ", vehicleTypeId=" + vehicleTypeId +
                ", reportDate=" + reportDate +
                ", vehicleTypeName='" + vehicleTypeName + '\'' +
                ", pricePerType=" + pricePerType +
                ", sumTotalPrice=" + sumTotalPrice +
                '}';
    }
}
