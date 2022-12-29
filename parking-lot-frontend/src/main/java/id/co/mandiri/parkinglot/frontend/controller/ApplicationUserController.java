package id.co.mandiri.parkinglot.frontend.controller;


import id.co.mandiri.parkinglot.backend.model.VehicleType;
import id.co.mandiri.parkinglot.backend.model.system.ApplicationUser;
import id.co.mandiri.parkinglot.backend.repository.system.ApplicationRoleRepository;
import id.co.mandiri.parkinglot.service.ApplicationUserService;
import id.co.mandiri.parkinglot.service.wrapper.ApplicationUserWrapper;
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
@RequestMapping(value = "/user")
public class ApplicationUserController {

    private static Logger logger = LogManager.getLogger(ApplicationUserController.class.getName());


    @Autowired
    private ApplicationUserService applicationUserService;



    @GetMapping(value = "/list")
    public String getList(Map<String, Object> mapParam){
        /*mapParam.put("model", new ApplicationUser());*/
        mapParam.put("userList", applicationUserService.getList());
        return "web/user/list";
    }

    @GetMapping(value = "/edit")
    public String editUser(Map<String, Object> mapParam, @RequestParam Long id){
        ApplicationUserWrapper rModel = applicationUserService.getById(id);
        mapParam.put("model", rModel);

        return "web/user/edit";
    }

    @PostMapping(value = "/add")
    public @ResponseBody Map<String, Object> add(ApplicationUserWrapper wrapper){
        Map<String, Object> rMap = new HashMap<>();
        rMap.put("action", "add");

        wrapper = applicationUserService.save(wrapper);
        if (wrapper.getId() != null){
            rMap.put("result", "message");
            rMap.put("success", "Data has been saved ");
        } else {
            rMap.put("result", "message");
            rMap.put("failed", "Data Book failed to save");
        }

        return rMap;
    }

    @PostMapping(value = "/update")
    public @ResponseBody Map<String, Object> update(ApplicationUserWrapper wrapper){
        Map<String, Object> rMap = new HashMap<>();
        rMap.put("action", "update");

        wrapper = applicationUserService.save(wrapper);
        if (wrapper.getId() != null){
            rMap.put("result", "message");
            rMap.put("success", "Data has been updated");
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
