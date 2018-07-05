package alessandrofook.contrato.repository;

import alessandrofook.contrato.model.pessoa.FuncaoPessoa;
import alessandrofook.contrato.model.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CarregadorDePessoa implements ApplicationRunner {

    private final PessoaRepository repository;

    @Autowired
    public CarregadorDePessoa(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Pessoa admin = new Pessoa();
        admin.setFuncao(FuncaoPessoa.ADMIN);
        admin.setNome("Admin");
        admin.setSenha("Admin");
        admin.setStatusDePagamento(true);
        repository.save(admin);

        Pessoa user = new Pessoa();
        user.setFuncao(FuncaoPessoa.USUARIO);
        user.setNome("User");
        user.setSenha("Admin");
        user.setStatusDePagamento(true);
        repository.save(user);

    }
}
