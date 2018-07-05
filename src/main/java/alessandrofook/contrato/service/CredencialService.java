package alessandrofook.contrato.service;

import alessandrofook.contrato.excecoes.CadastroDeCredencialException;
import alessandrofook.contrato.excecoes.DeletarCredencialDeUsuarioException;
import alessandrofook.contrato.excecoes.EdicaoDeCredencialException;
import alessandrofook.contrato.model.autenticacao.Credencial;
import alessandrofook.contrato.model.autenticacao.Role;
import alessandrofook.contrato.model.pessoa.Pessoa;
import alessandrofook.contrato.repository.CredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredencialService {

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

  public void cadastrarCredencial(Credencial credencial) {
    if(credencial.getRole().equals(Role.USUARIO)) {
      throw new CadastroDeCredencialException();
    }
    repository.save(credencial);
  }

  public void editarCredencial(Credencial credencial) {

    Credencial credencialArmazenada = repository.findByLogin(credencial.getLogin());
    if(credencial.getRole().equals(credencialArmazenada.getRole())) {
      credencial.setId(credencialArmazenada.getId());
      repository.save(credencial);

    } else {
      throw new EdicaoDeCredencialException();
    }
  }

  public void removerCredencialDeAdmin(String login) {

    Credencial credencial = repository.findByLogin(login);

    if(credencial.getRole().equals(Role.ADMIN)) {
      repository.deleteByLogin(login);

    } else {
      throw new DeletarCredencialDeUsuarioException();
    }
  }
}
