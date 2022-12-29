package id.co.mandiri;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class ParkingLotApplication {

	private static Logger logger = LogManager.getLogger(ParkingLotApplication.class.getName());


	public static void main(String[] args) {
		String dataStarted = new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(new Date());
		logger.info("Application Parking Lot Started at : {}", dataStarted);
		SpringApplication.run(ParkingLotApplication.class, args);
	}

}
