package alessandrofook.contrato.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EdicaoDeCredencialException extends RuntimeException {

  public EdicaoDeCredencialException() {
    super("Não é possível editar a credencial nesta requisição"
        + " para modificar o nível de acesso ao sistema!");
  }
}
