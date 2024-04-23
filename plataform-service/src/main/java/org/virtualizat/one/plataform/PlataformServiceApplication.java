package org.virtualizat.one.plataform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
public class PlataformServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(PlataformServiceApplication.class, args);
	}

}
