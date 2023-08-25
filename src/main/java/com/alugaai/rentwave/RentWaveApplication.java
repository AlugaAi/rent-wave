package com.alugaai.rentwave;

import com.alugaai.rentwave.lib.app.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentWaveApplication extends Application {

	public static void main(String[] args) {
		SpringApplication.run(RentWaveApplication.class, args);
	}

}
