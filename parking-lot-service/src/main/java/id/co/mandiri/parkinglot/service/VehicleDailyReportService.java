package id.co.mandiri.parkinglot.service;

import id.co.mandiri.parkinglot.backend.model.VehicleType;
import id.co.mandiri.parkinglot.backend.model.reporting.VehicleDailyReport;
import id.co.mandiri.parkinglot.backend.repository.VehicleTypeRepository;
import id.co.mandiri.parkinglot.backend.repository.reporting.VehicleDailyReportRepository;
import id.co.mandiri.parkinglot.service.wrapper.VehicleDailyReportWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class VehicleDailyReportService  {

    private VehicleDailyReportRepository vehicleDailyReportRepository;

    private VehicleTypeRepository vehicleTypeRepository;

    public VehicleDailyReportService(VehicleDailyReportRepository vehicleDailyReportRepository, VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleDailyReportRepository = vehicleDailyReportRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }


    private VehicleDailyReport toEntity(VehicleDailyReportWrapper wrapper){
        VehicleDailyReport model = new VehicleDailyReport();
        if (wrapper.getId() !=null){
            Optional<VehicleDailyReport> optModel = vehicleDailyReportRepository.findById(wrapper.getId());
            if (optModel.isPresent()){
                model = optModel.get();
            }
        }

        model.setReportDate(wrapper.getReportDate());
        model.setSumTotalPrice(wrapper.getSumTotalPrice());
        if (wrapper.getVehicleTypeId() != null){
            Optional<VehicleType> optTypeModel = vehicleTypeRepository.findById(wrapper.getVehicleTypeId());
            if (optTypeModel.isPresent()){
                model.setVehicleType(optTypeModel.get());
            }
        }

        return model;

    }

    private VehicleDailyReportWrapper toWrapper(VehicleDailyReport dataModel) {
        VehicleDailyReportWrapper wrapper = new VehicleDailyReportWrapper();
        wrapper.setId(dataModel.getId());
        wrapper.setReportDate(dataModel.getReportDate());
        wrapper.setSumTotalPrice(dataModel.getSumTotalPrice());
        wrapper.setVehicleTypeId(dataModel.getVehicleType().getId());
        wrapper.setVehicleTypeName(dataModel.getVehicleType().getTypeVehicle());

        return wrapper;
    }

    private List<VehicleDailyReportWrapper> toWrapperList(List<VehicleDailyReport> modelList){
        List<VehicleDailyReportWrapper> rList = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()){
            for (VehicleDailyReport model : modelList){
                rList.add(toWrapper(model));
            }
        }

        return rList;
    }


    public Long getNum() {
        return vehicleDailyReportRepository.count();
    }

    public VehicleDailyReportWrapper save(VehicleDailyReportWrapper wrapper){
        return toWrapper(vehicleDailyReportRepository.save(toEntity(wrapper)));
    }

    public VehicleDailyReportWrapper findById(Long id){
        Optional<VehicleDailyReport> optModel = vehicleDailyReportRepository.findById(id);
        return optModel.map(this::toWrapper).orElse(null);
    }

    public Boolean delete(Long id){
        return null;
    }

    public List<VehicleDailyReportWrapper> findAll(){
        return toWrapperList((List<VehicleDailyReport>) vehicleDailyReportRepository.findAll());
    }




}
