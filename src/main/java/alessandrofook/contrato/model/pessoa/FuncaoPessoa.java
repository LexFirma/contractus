package alessandrofook.contrato.model.pessoa;

import java.util.Arrays;

public enum FuncaoPessoa {
    ADMIN("ADMIN"), USUARIO("USUARIO");

    private String valor;

    private FuncaoPessoa(String valor) {
        this.valor = valor;
    }

    /**
     * Método que retorna o enum correspondente a funcao contida no parâmetro.
     * @param funcao - String que represente o nome da função a ser recuperada.
     * @return Um Enum da função correspondente ao parâmetro.
     */
    public static FuncaoPessoa selecionarFuncao(String funcao) {

        for (FuncaoPessoa funcaoUsuario : values()) {
            if (funcaoUsuario.valor.equalsIgnoreCase(funcao)) {
                return funcaoUsuario;
            }
        }

        throw new IllegalArgumentException(
                "Funcao nao cadastrada no sistema " + funcao + ", as funções válidas são " + Arrays
                        .toString(values()));
    }
}
