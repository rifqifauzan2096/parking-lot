package id.co.mandiri.parkinglot.frontend.controller;


import id.co.mandiri.parkinglot.backend.model.ParkingLot;
import id.co.mandiri.parkinglot.backend.repository.VehicleTypeRepository;
import id.co.mandiri.parkinglot.service.ParkingLotService;
import id.co.mandiri.parkinglot.service.wrapper.ParkingLotWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Controller
@RequestMapping(value = "/parking")
public class ParkingLotController {

    private static Logger logger = LogManager.getLogger(ParkingLotController.class.getName());

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;


    @GetMapping(value = "/new")
    public String newVehicle(Map<String, Object> mapParam){
        mapParam.put("model", new ParkingLot());
        mapParam.put("vehicleType", vehicleTypeRepository.findAll());
        return "/web/parkinglot/new";
    }

    @GetMapping(value = "/list")
    public String getList(Map<String, Object> mapParam){
        long startTime = System.currentTimeMillis();

        mapParam.put("model", new ParkingLot());
        mapParam.put("parkingList", parkingLotService.findStatus("no"));
        mapParam.put("vehicleType", vehicleTypeRepository.findAll());
        mapParam.put("startTime", new SimpleDateFormat("HH:mm:ss").format(new Date()));

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        mapParam.put("resultTime", resultTime);
        logger.info("Processing Time, {}", resultTime);

        return "/web/parkinglot/list";
    }

    @GetMapping(value = "/completed")
    public String getCompleted(Map<String, Object> mapParam){
        mapParam.put("listByStatus", parkingLotService.findStatus("yes"));
        return "/web/parkinglot/completed";
    }

    @GetMapping(value = "/edit")
    public String editVehicle(Map<String, Object> mapParam, @RequestParam Long id){
        ParkingLotWrapper rModel = parkingLotService.getById(id);
        mapParam.put("model", rModel);
        mapParam.put("endTime", new SimpleDateFormat("hh:mm:ss a").format(new Date()));

        mapParam.put("totalPrice", parkingLotService.calculate(rModel));

        return "/web/parkinglot/edit";
    }

    @PostMapping(value = "/add")
    public @ResponseBody
    Map<String, Object> add(ParkingLotWrapper wrapper){
        Map<String, Object> rMap = new HashMap<>();
        rMap.put("action", "add");

        wrapper = parkingLotService.save(wrapper);
        if (wrapper.getId() != null){
            rMap.put("result", "message");
            rMap.put("success", "Data has been saved successfully");
        } else {
            rMap.put("result", "message");
            rMap.put("failed", "Data Book failed to save");
        }

        return rMap;
    }


    @PostMapping(value = "/update")
    public @ResponseBody Map<String, Object> update(ParkingLotWrapper wrapper){
        Map<String, Object> rMap = new HashMap<>();
        rMap.put("action", "update");

        wrapper = parkingLotService.save(wrapper);
        if (wrapper.getId() != null){
            rMap.put("result", "message");
            rMap.put("success", "Data has been updated successfully");
        } else {
            rMap.put("result", "message");
            rMap.put("failed", "Data Book failed to update");
        }

        return rMap;
    }

    @PostMapping(value = "/changeLang")
    public @ResponseBody
    String changeLang(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) String lang) {
        logger.info(new StringBuilder().append("Locale Changed to ").append(lang));
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setLocale(request, response, Locale.forLanguageTag(lang));
        return "Locale Changed to : " + lang;
    }
}
