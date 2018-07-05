package alessandrofook.contrato.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PessoaInexistenteException extends RuntimeException {
    public PessoaInexistenteException() {
        super("Pessoa não cadastrada no sistema!");
    }
}
