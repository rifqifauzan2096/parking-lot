package id.co.mandiri.parkinglot.backend.repository.system;

import id.co.mandiri.parkinglot.backend.model.system.ApplicationUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

}
