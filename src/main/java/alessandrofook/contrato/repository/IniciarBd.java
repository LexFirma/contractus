package alessandrofook.contrato.repository;

import alessandrofook.contrato.model.autenticacao.Credencial;
import alessandrofook.contrato.model.autenticacao.Role;
import alessandrofook.contrato.model.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class IniciarBd implements ApplicationRunner {

  private final CredencialRepository credencialRepository;

  @Autowired
  public IniciarBd(PessoaRepository pessoaRepository, CredencialRepository credencialRepository) {
    this.credencialRepository = credencialRepository;
  }

  @Override
  public void run(ApplicationArguments applicationArguments) throws Exception {

    Credencial adminCd = new Credencial();
    adminCd.setUsername("Admin");
    adminCd.setPassword(new BCryptPasswordEncoder().encode("admin"));
    adminCd.setRole(Role.ROLE_ADMIN);
    credencialRepository.save(adminCd);

  }
}
