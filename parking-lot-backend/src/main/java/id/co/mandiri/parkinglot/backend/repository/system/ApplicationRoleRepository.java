package id.co.mandiri.parkinglot.backend.repository.system;

import id.co.mandiri.parkinglot.backend.model.system.ApplicationRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApplicationRoleRepository extends CrudRepository<ApplicationRole, Long> {

    @Query("select u from ApplicationRole u where u.username=:username")
    List<ApplicationRole> getByUsername(@Param("username") String username);

    /*@Query(value = "select a from ApplicationRole a where a.username=:username and a.role=:role")
    ApplicationRole getByUsernameRole(@Param("username") String username, @Param("role") String role);*/
}
