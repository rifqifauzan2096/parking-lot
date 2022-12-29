package id.co.mandiri.parkinglot.service;

import id.co.mandiri.parkinglot.backend.model.VehicleType;
import id.co.mandiri.parkinglot.backend.model.system.ApplicationRole;
import id.co.mandiri.parkinglot.backend.model.system.ApplicationUser;
import id.co.mandiri.parkinglot.backend.repository.system.ApplicationRoleRepository;
import id.co.mandiri.parkinglot.backend.repository.system.ApplicationUserRepository;
import id.co.mandiri.parkinglot.service.wrapper.ApplicationUserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationRoleRepository applicationRoleRepository;

    public ApplicationUserService(ApplicationUserRepository applicationUserRepository, ApplicationRoleRepository applicationRoleRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.applicationRoleRepository = applicationRoleRepository;
    }

    private ApplicationUser toEntity(ApplicationUserWrapper wrapper){
        ApplicationUser model = new ApplicationUser();

        if (wrapper.getId() != null){
            model = applicationUserRepository.findById(wrapper.getId()).get();
        }

        model.setUsername(wrapper.getUsername());
        model.setPassword(wrapper.getPassword());
        model.setEmail(wrapper.getEmail());
        model.setEnabled(wrapper.getEnabled());

        return model;
    }

    private ApplicationUserWrapper toWrapper(ApplicationUser dataModel){
        ApplicationUserWrapper wrapper = new ApplicationUserWrapper();

        wrapper.setId(dataModel.getId());
        wrapper.setUsername(dataModel.getUsername());
        wrapper.setPassword(dataModel.getPassword());
        wrapper.setEmail(dataModel.getEmail());
        wrapper.setEnabled(dataModel.getEnabled());


        List<ApplicationRole> roleList = applicationRoleRepository.getByUsername(wrapper.getUsername());
        if (roleList != null && !roleList.isEmpty()){
            for (ApplicationRole role : roleList){
                wrapper.addRole(role.getRole());
            }

        }
        return wrapper;
    }

    private List<ApplicationUserWrapper> toWrapperList(List<ApplicationUser> modelList){
        List<ApplicationUserWrapper> rList = new ArrayList<>();

        if (modelList != null && !modelList.isEmpty()){
            for (ApplicationUser model : modelList){
                rList.add(toWrapper(model));
            }
        }

        return rList;
    }


    public ApplicationUserWrapper save(ApplicationUserWrapper wrapper){
        return toWrapper(applicationUserRepository.save(toEntity(wrapper)));
    }

    public List<ApplicationUserWrapper> getList(){
        return toWrapperList((List<ApplicationUser>) applicationUserRepository.findAll());
    }

    public ApplicationUserWrapper getById(Long id){
        return toWrapper(applicationUserRepository.findById(id).get());
    }


}
