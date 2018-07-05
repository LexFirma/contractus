package alessandrofook.contrato.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EdicaoDePessoaException extends RuntimeException {

  public EdicaoDePessoaException(String atributo) {
    super("O atributo " + atributo + "não pode ser modificado por essa request!");
  }
}
