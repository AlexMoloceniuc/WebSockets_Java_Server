package ro.space.wserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(WserverApplication.class, args);
	}

}
