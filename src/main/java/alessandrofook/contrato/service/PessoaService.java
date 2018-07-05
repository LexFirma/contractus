package alessandrofook.contrato.service;

import alessandrofook.contrato.excecoes.CadastroException;
import alessandrofook.contrato.excecoes.PessoaInexistenteException;
import alessandrofook.contrato.model.pessoa.Pessoa;
import alessandrofook.contrato.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa cadastrarPessoa(Pessoa pessoa) {

        if(pessoa.getId() != null) {
            throw new CadastroException("O valor do id deve ser selecionado pelo servidor!");

        } else if (repository.existsByNome(pessoa.getNome())) {
            throw new CadastroException("Pessoa já existe no sistema");

        } else {
            return repository.save(pessoa);
        }
    }

    public Pessoa mudarStatusDePagamento(Long id) {

        if(!repository.existsById(id)) {
            throw new PessoaInexistenteException();
        }

        Pessoa pessoa = repository.findById(id).get();
        pessoa.setStatusDePagamento(!pessoa.isStatusDePagamento());

        return repository.save(pessoa);
    }

    public void removerPessoa(Long id) {
        if(!repository.existsById(id)) {
            throw new PessoaInexistenteException();
        } else {
            repository.deleteById(id);
        }
    }

    public List<Pessoa> listarPessoas() {
        return repository.findAll();
    }
}
