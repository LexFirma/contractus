package alessandrofook.contrato.service;

import alessandrofook.contrato.excecoes.CadastroDeCredencialException;
import alessandrofook.contrato.excecoes.DeletarCredencialDeUsuarioException;
import alessandrofook.contrato.excecoes.EdicaoDeCredencialException;
import alessandrofook.contrato.model.autenticacao.Credencial;
import alessandrofook.contrato.model.autenticacao.Role;
import alessandrofook.contrato.model.pessoa.Pessoa;
import alessandrofook.contrato.repository.CredencialRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CredencialService implements UserDetailsService {

  @Autowired
  private CredencialRepository repository;

  public void gerarCredencialDePessoa(Pessoa pessoa) {
    Credencial credencial = new Credencial(pessoa);
    repository.save(credencial);
  }

  public void removerCredencialDePessoa(Long pessoaId) {
    System.out.println("Pessoa id: " + pessoaId);
    repository.deleteByPessoaId(pessoaId);
  }

  /**
   * Método que cria uma credencial de administrador no sistema.
   * @param credencial - Objeto do tipo de Credencial que deve ter a Role de ADMIN.
   */
  public void cadastrarCredencial(Credencial credencial) {
    if (credencial.getRole().equals(Role.ROLE_USUARIO)) {
      throw new CadastroDeCredencialException();
    }
    repository.save(credencial);
  }

  /**
   * Método para modificação de credenciais existentes no sistema, não podendo modificar a Role.
   * @param credencial - Objeto contendo informações a serem atualizadas no registro da credencial.
   */
  public void editarCredencial(Credencial credencial, String login) {

    Credencial credencialArmazenada = repository.findByUsername(login);
    if (credencial.getRole().equals(credencialArmazenada.getRole())) {
      credencial.setId(credencialArmazenada.getId());
      repository.save(credencial);

    } else {
      throw new EdicaoDeCredencialException();
    }
  }

  /**
   * Método que remove uma credencial do sistema a partir do seu login.
   * @param login - Objeto do tipo String que identifica a credencial a ser removida.
   */
  public void removerCredencialDeAdmin(String login) {

    Credencial credencial = repository.findByUsername(login);

    if (credencial.getRole().equals(Role.ROLE_ADMIN)) {
      repository.deleteByUsername(login);

    } else {
      throw new DeletarCredencialDeUsuarioException();
    }
  }

  @Override
  public User loadUserByUsername(String s) throws UsernameNotFoundException {

    Credencial credencial = repository.findByUsername(s);
    List authList = AuthorityUtils.createAuthorityList(credencial.getRole().toString());

    return new User(credencial.getUsername(), credencial.getPassword(), authList);
  }
}
