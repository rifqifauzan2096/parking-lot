package id.co.mandiri.parkinglot.backend.repository;

import id.co.mandiri.parkinglot.backend.model.ParkingLot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ParkingLotRepository extends PagingAndSortingRepository<ParkingLot, Long > {

    @Query("select a from ParkingLot a where a.completed = :status")
    List<ParkingLot> findByStatus(@Param("status") String status);
}
