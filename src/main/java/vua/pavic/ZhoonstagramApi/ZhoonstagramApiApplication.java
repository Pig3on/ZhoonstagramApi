package vua.pavic.ZhoonstagramApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ZhoonstagramApiApplication {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("password"));
		SpringApplication.run(ZhoonstagramApiApplication.class, args);
	}

}
