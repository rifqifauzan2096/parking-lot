package id.co.mandiri.parkinglot.frontend.controller;

import id.co.mandiri.parkinglot.backend.model.VehicleType;
import id.co.mandiri.parkinglot.service.VehicleTypeService;
import id.co.mandiri.parkinglot.service.wrapper.VehicleTypeWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Controller
@RequestMapping(value = "/type")
public class VehicleTypeController {


    private static Logger logger = LogManager.getLogger(ParkingLotController.class.getName());

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @GetMapping(value = "/new")
    public String addType(Map<String, Object> mapParam){
        mapParam.put("model", new VehicleType());
        return "web/type/new";
    }

    @GetMapping(value = "/list")
    public String getList(Map<String, Object> mapParam){
        mapParam.put("typeList", vehicleTypeService.getList());
        mapParam.put("model", new VehicleType());
        return "web/type/list";
    }

    @GetMapping(value = "/edit")
    public String editType(@RequestParam Long id, Map<String, Object> mapParam){
        VehicleTypeWrapper rModel = vehicleTypeService.getById(id);
        mapParam.put("model", rModel);
        return "web/type/edit";
    }

    @PostMapping(value = "/add")
    public @ResponseBody Map<String, Object> add(VehicleTypeWrapper wrapper){
        Map<String, Object> rMap = new HashMap<>();
        rMap.put("action", "add");

        wrapper = vehicleTypeService.save(wrapper);
        if (wrapper.getId() != null){
            rMap.put("result", "message");
            rMap.put("success", "Data has been saved successfully");
        } else {
            rMap.put("result", "message");
            rMap.put("failed", "Data failed to add");
        }

        return rMap;
    }

    @PostMapping(value = "/update")
    public @ResponseBody Map<String, Object> update(VehicleTypeWrapper wrapper){
        Map<String, Object> rMap = new HashMap<>();
        rMap.put("action", "update");

        wrapper = vehicleTypeService.save(wrapper);
        if (wrapper.getId() !=null){
            rMap.put("result", "success");
            rMap.put("message", "Data has been update successfully");
        } else {
            rMap.put("result", "failed");
            rMap.put("message", "Data failed to update");
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
