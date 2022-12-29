package id.co.mandiri.parkinglot.service;


import id.co.mandiri.parkinglot.backend.model.VehicleType;
import id.co.mandiri.parkinglot.backend.repository.VehicleTypeRepository;
import id.co.mandiri.parkinglot.service.wrapper.VehicleTypeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    private VehicleType toEntity(VehicleTypeWrapper wrapper){
        VehicleType model = new VehicleType();

        if(wrapper.getId() != null){
            model = vehicleTypeRepository.findById(wrapper.getId()).get();
        }

        model.setTypeVehicle(wrapper.getTypeVehicle());
        model.setPricePerHour(wrapper.getPricePerHour());

        return model;
    }

    private VehicleTypeWrapper toWrapper(VehicleType entity){
        VehicleTypeWrapper wrapper = new VehicleTypeWrapper();

        wrapper.setId(entity.getId());
        wrapper.setTypeVehicle(entity.getTypeVehicle());
        wrapper.setPricePerHour(entity.getPricePerHour());

        return wrapper;
    }

    private List<VehicleTypeWrapper> toWrapperList(List<VehicleType> vehicleTypeList){
        List<VehicleTypeWrapper> rList = new ArrayList<>();

        if(vehicleTypeList != null && !vehicleTypeList.isEmpty()){
            for (VehicleType model : vehicleTypeList){
                rList.add(toWrapper(model));
            }
        }

        return rList;
    }


    public VehicleTypeWrapper save(VehicleTypeWrapper wrapper) {
        return toWrapper(vehicleTypeRepository.save(toEntity(wrapper)));
    }

    public List<VehicleTypeWrapper> getList(){
        return toWrapperList((List<VehicleType>) vehicleTypeRepository.findAll());
    }

    public VehicleTypeWrapper getById(Long id) {
        return toWrapper(vehicleTypeRepository.findById(id).get());
    }

}
