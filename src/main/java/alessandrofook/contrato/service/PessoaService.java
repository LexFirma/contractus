package alessandrofook.contrato.service;

import alessandrofook.contrato.excecoes.CadastroException;
import alessandrofook.contrato.excecoes.PessoaInexistenteException;
import alessandrofook.contrato.model.pessoa.Pessoa;
import alessandrofook.contrato.repository.PessoaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PessoaService {

  @Autowired
  private PessoaRepository repository;

  /**
   * Realiza o cadastro de uma nova pessoa no banco de dados do sistema.
   * @param pessoa - Objeto do tipo pessoa a ser cadastrado no sistema.
   * @return Objeto do tipo pessoa conforme se encontra no banco de dados.
   */
  public Pessoa cadastrarPessoa(Pessoa pessoa) {

    if (pessoa.getId() != null) {
      throw new CadastroException("O valor do id deve ser selecionado pelo servidor!");

    } else if (repository.existsByNome(pessoa.getNome())) {
      throw new CadastroException("Pessoa já existe no sistema");

    } else {
      return repository.save(pessoa);
    }
  }

  /**
   * Modifica o valor do status de pagamento para o valor lógico oposto.
   * @param id - chave de registro do usuário a ser objeto da alteração.
   * @return Objeto do tipo Pessoa com a alteração do status de pagamento.
   */
  public Pessoa mudarStatusDePagamento(Long id) {

    if (!repository.existsById(id)) {
      throw new PessoaInexistenteException();
    }

    Pessoa pessoa = repository.findById(id).get();
    pessoa.setStatusDePagamento(!pessoa.isStatusDePagamento());

    return repository.save(pessoa);
  }

  /**
   * Remove um determinado usuário do sistema.
   * @param id - chave de registro do usuário a ser objeto da alteração.
   */
  public void removerPessoa(Long id) {
    if (!repository.existsById(id)) {
      throw new PessoaInexistenteException();
    } else {
      repository.deleteById(id);
    }
  }

  public List<Pessoa> listarPessoas() {
    return repository.findAll();
  }
}
