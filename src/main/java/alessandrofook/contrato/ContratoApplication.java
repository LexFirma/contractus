package alessandrofook.contrato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
public class ContratoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ContratoApplication.class, args);
  }

  @RequestMapping("/home")
  public String hello() {
    return "Hello buddy!";
  }

  @RequestMapping("/users")
  @ResponseBody
  public String getUsers() {
    return "{\"users\":[{\"name\":\"Lucas\", \"country\":\"Brazil\"}," +
        "{\"name\":\"Jackie\",\"country\":\"China\"}]}";
  }

}
