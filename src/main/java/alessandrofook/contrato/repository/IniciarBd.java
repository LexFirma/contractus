package alessandrofook.contrato.repository;

import alessandrofook.contrato.model.autenticacao.Credencial;
import alessandrofook.contrato.model.autenticacao.Role;
import alessandrofook.contrato.model.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class IniciarBd implements ApplicationRunner {

  private final PessoaRepository pessoaRepository;

  private final CredencialRepository credencialRepository;

  @Autowired
  public IniciarBd(PessoaRepository pessoaRepository, CredencialRepository credencialRepository) {
    this.pessoaRepository = pessoaRepository;
    this.credencialRepository = credencialRepository;
  }

  @Override
  public void run(ApplicationArguments applicationArguments) throws Exception {

    Pessoa admin = new Pessoa();
    admin.setNome("Admin");
    admin.setStatusDePagamento(true);
    pessoaRepository.save(admin);

    Credencial adminCd = new Credencial();
    adminCd.setLogin("Admin");
    adminCd.setSenha("admin");
    adminCd.setPessoa(admin);
    adminCd.setRole(Role.ADMIN);
    credencialRepository.save(adminCd);

    Pessoa user = new Pessoa();
    user.setNome("User");
    user.setStatusDePagamento(true);
    pessoaRepository.save(user);

    Credencial userCd = new Credencial();
    userCd.setLogin("User");
    userCd.setSenha("admin");
    userCd.setRole(Role.USUARIO);
    userCd.setPessoa(user);
    credencialRepository.save(userCd);
  }
}
