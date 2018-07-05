package alessandrofook.contrato.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CadastroException extends RuntimeException {
    public CadastroException(String motivo) {
        super("Erro no cadastro: " + motivo);
    }
}
