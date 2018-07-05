package alessandrofook.contrato.service;

import alessandrofook.contrato.model.autenticacao.Credencial;
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
}
