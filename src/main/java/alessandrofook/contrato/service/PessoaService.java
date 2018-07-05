package alessandrofook.contrato.service;

import alessandrofook.contrato.excecoes.CadastroException;
import alessandrofook.contrato.model.pessoa.FuncaoPessoa;
import alessandrofook.contrato.model.pessoa.Pessoa;
import alessandrofook.contrato.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa cadastrarPessoa(Pessoa novaPessoa) {

        if(novaPessoa.getId() != null) {
            throw new CadastroException("O id não pode ser definido fora do servidor.");

        } else if (repository.existsByNome(novaPessoa.getNome())) {
            throw new CadastroException("Usuário já existe no sistema!");

        } else {
            return repository.save(novaPessoa);
        }
    }

    public void removerPessoa(Long id) {
        repository.deleteById(id);
    }

    public List<Pessoa> listarPessoas() { return repository.findAll();}

    public Pessoa mudarStatusDePagamento(Long id) {
        Pessoa pessoa = repository.getOne(id);
        pessoa.setStatusDePagamento(!pessoa.isStatusDePagamento());
        return repository.save(pessoa);
    }

}
