package net.pdrtechnology.mysql.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlApplication.class, args);
		System.out.println("Hello spring boot");
	}

}
