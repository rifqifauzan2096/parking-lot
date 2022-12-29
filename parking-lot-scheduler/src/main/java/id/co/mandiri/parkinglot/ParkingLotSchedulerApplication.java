package id.co.mandiri.parkinglot;

import id.co.mandiri.parkinglot.scheduler.SummaryReport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ParkingLotSchedulerApplication implements CommandLineRunner {

    final SummaryReport summaryReport;

    public ParkingLotSchedulerApplication(SummaryReport summaryReport){
        this.summaryReport = summaryReport;
    }

    @Override
    public void run(String... agrs) throws Exception{
        summaryReport.initiateSchedule();
    }

    public static void main(String[] args) {
        SpringApplication.run(ParkingLotSchedulerApplication.class);
    }
}
