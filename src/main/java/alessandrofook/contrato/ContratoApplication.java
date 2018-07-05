package alessandrofook.contrato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class ContratoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContratoApplication.class, args);
	}

	@GetMapping("/home")
	@ResponseBody
	public String hello() {
		return "Hello buddy!";
	}

}
