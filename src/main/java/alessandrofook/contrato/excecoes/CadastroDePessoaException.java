package alessandrofook.contrato.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CadastroDePessoaException extends RuntimeException {

  public CadastroDePessoaException(String motivo) {
    super("Erro no cadastro: " + motivo);
  }
}
