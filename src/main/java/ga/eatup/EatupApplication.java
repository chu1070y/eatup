package ga.eatup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"ga.eatup.user.mapper", "ga.eatup.partner.mapper"})
public class EatupApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatupApplication.class, args);
	}
}
