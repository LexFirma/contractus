package alessandrofook.contrato.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeletarCredencialDeUsuarioException extends RuntimeException {
  public DeletarCredencialDeUsuarioException() {
    super("Não é possível deletar uma credencial de usuário utilizando essa rota!");
  }

}
