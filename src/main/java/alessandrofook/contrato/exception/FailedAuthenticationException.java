package alessandrofook.contrato.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.security.core.AuthenticationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public FailedAuthenticationException() {
        super("Bad credentials.");
    }

    public FailedAuthenticationException(String errorMenssage) {
        super(errorMenssage);
    }
}
