package alessandrofook.contrato.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CadastroDeCredencialException extends RuntimeException {

  public CadastroDeCredencialException() {
    super("Para criar uma credencial de usuário, utilize a chamada de criação de pessoa!");
  }

}
