package id.co.mandiri.parkinglot.service.wrapper;



public class VehicleTypeWrapper {

    private Long id;
    private String typeVehicle;
    private Double pricePerHour;

    public VehicleTypeWrapper() {
    }

    public VehicleTypeWrapper(Long id, String typeVehicle, Double pricePerHour) {
        this.id = id;
        this.typeVehicle = typeVehicle;
        this.pricePerHour = pricePerHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
