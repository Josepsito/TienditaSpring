package com.deconfort.tiendita;

import com.deconfort.tiendita.service.ProductService;
import com.deconfort.tiendita.service.impl.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TienditaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TienditaApplication.class, args);
	}

}
