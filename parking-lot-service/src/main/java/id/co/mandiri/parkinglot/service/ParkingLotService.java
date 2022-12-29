package id.co.mandiri.parkinglot.service;


import id.co.mandiri.parkinglot.backend.model.ParkingLot;
import id.co.mandiri.parkinglot.backend.model.VehicleType;
import id.co.mandiri.parkinglot.backend.repository.ParkingLotRepository;
import id.co.mandiri.parkinglot.backend.repository.VehicleTypeRepository;
import id.co.mandiri.parkinglot.service.wrapper.ParkingLotWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    private ParkingLot toEntity(ParkingLotWrapper wrapper){
        ParkingLot model = new ParkingLot();

        if (wrapper.getId() != null){
            model = parkingLotRepository.findById(wrapper.getId()).get();
        }

        Double price = wrapper.getTotalPrice();
        if (price == 0){
            model.setStartTime(new Date());
        } else {
            model.setEndTime(new Date());
            model.setTotalPrice(price);
        }

        model.setCompleted(wrapper.getCompleted());
        model.setVehicleNumber(wrapper.getVehicleNumber());
        Optional<VehicleType> optVehicleType = vehicleTypeRepository.findById(wrapper.getVehicleTypeId());
        model.setVehicleType(optVehicleType.get());

        return model;
    }

    private ParkingLotWrapper toWrapper(ParkingLot dataModel) {
        ParkingLotWrapper wrapper = new ParkingLotWrapper();
        wrapper.setId(dataModel.getId());
        wrapper.setStartTime(dataModel.getStartTime());
        wrapper.setEndTime(dataModel.getEndTime());
        wrapper.setVehicleNumber(dataModel.getVehicleNumber());
        wrapper.setCompleted(dataModel.getCompleted());
        wrapper.setTotalPrice(dataModel.getTotalPrice());
        wrapper.setVehicleTypeId(dataModel.getVehicleType().getId());
        wrapper.setPricePerHour(dataModel.getVehicleType().getPricePerHour());
        wrapper.setVehicleTypeName(dataModel.getVehicleType().getTypeVehicle());

        return wrapper;
    }

    private List<ParkingLotWrapper> toWrapperList(List<ParkingLot> modelList){
        List<ParkingLotWrapper> rList = new ArrayList<>();
        if (modelList !=null && !modelList.isEmpty()){
            for (ParkingLot model : modelList){
                rList.add(toWrapper(model));
            }
        }

        return rList;
    }


    public ParkingLotWrapper save(ParkingLotWrapper wrapper) {
        return toWrapper(parkingLotRepository.save(toEntity(wrapper)));
    }

    public List<ParkingLotWrapper> getList(){
        return toWrapperList((List<ParkingLot>) parkingLotRepository.findAll());
    }

    public List<ParkingLotWrapper> findStatus(String status){
        return toWrapperList((List<ParkingLot>) parkingLotRepository.findByStatus(status));
    }

    public ParkingLotWrapper getById(Long id) {
        return toWrapper(parkingLotRepository.findById(id).get());
    }

    /*Special method*/
    public Double calculate(ParkingLotWrapper rModel) {

        Date end_time = new Date();
        Date start_time = rModel.getStartTime();
        long duration = end_time.getTime() - start_time.getTime();
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
        long finalDuration;
        if(diffInMinutes < 60) {
            finalDuration = diffInHours+1;
        } else if((diffInMinutes%60 != 0)){
            finalDuration = diffInHours+1;
        } else {
            finalDuration = diffInHours;
        }
        return rModel.getPricePerHour()*finalDuration;
    }
}
