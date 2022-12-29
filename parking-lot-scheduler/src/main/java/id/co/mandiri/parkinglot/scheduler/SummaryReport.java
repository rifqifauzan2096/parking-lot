package id.co.mandiri.parkinglot.scheduler;

import id.co.mandiri.parkinglot.service.ParkingLotService;
import id.co.mandiri.parkinglot.service.VehicleDailyReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class SummaryReport {

    private Logger logger = LogManager.getLogger(SummaryReport.class.getName());

    private VehicleDailyReportService vehicleDailyReportService;
    private ParkingLotService parkingLotService;

    public SummaryReport(VehicleDailyReportService vehicleDailyReportService, ParkingLotService parkingLotService) {
        this.vehicleDailyReportService = vehicleDailyReportService;
        this.parkingLotService = parkingLotService;
    }

    public void initiateSchedule() {
        if (logger.isInfoEnabled()){
            logger.info("Schedule Initiated on : {}", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void doDailySummaryForVehicleTransaction(){
        Date today = new Date();
        logger.info("Do Daily Summary Report Run on : {}", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()) );

        // 1. temukan baris data yang tercatat transaksi current day-1
        // 2. insert summary data berdasarkan no. 1
    }
}
