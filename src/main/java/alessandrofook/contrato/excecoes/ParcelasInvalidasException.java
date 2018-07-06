package alessandrofook.contrato.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParcelasInvalidasException extends RuntimeException {
  public ParcelasInvalidasException() {
    super("A soma dos valores das parcelas deve ser igual ao valor total do contrato!");
  }
}
