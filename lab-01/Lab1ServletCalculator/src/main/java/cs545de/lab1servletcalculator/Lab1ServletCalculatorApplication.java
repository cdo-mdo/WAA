package cs545de.lab1servletcalculator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Lab1ServletCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab1ServletCalculatorApplication.class, args);
	}

}
