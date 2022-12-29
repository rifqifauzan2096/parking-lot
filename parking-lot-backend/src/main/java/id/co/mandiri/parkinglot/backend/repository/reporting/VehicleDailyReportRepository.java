package id.co.mandiri.parkinglot.backend.repository.reporting;

import id.co.mandiri.parkinglot.backend.model.reporting.VehicleDailyReport;
import org.springframework.data.repository.CrudRepository;

public interface VehicleDailyReportRepository extends CrudRepository<VehicleDailyReport, Long> {
}
