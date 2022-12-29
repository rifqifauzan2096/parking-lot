package id.co.mandiri.parkinglot.backend.repository;

import id.co.mandiri.parkinglot.backend.model.VehicleType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleTypeRepository extends PagingAndSortingRepository<VehicleType, Long> {
}
