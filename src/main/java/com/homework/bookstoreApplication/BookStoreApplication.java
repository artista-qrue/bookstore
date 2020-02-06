package com.homework.bookstoreApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.homework")//Bu anotasyon bu projenin bir spring uygulaması olduğunu bildiriyor.
@ComponentScan(basePackages = {"com.homework"}) //component scan ile tüm yaratılmış beanleri com.homework paketi altına aramımıza izin veriyor
@EntityScan("com.homework.model") 
public class BookStoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookStoreApplication.class, args);
	}

}
