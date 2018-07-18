package alessandrofook.contrato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
public class ContratoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ContratoApplication.class, args);
  }
}
