package alessandrofook.contrato.model;

import alessandrofook.contrato.model.pessoa.Pessoa;
import alessandrofook.contrato.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa cadastrarPessoa(Pessoa novaPessoa) {
        return repository.save(novaPessoa);
    }

    public void removerPessoa(Long id) {
        repository.deleteById(id);
    }

    public void editarStatus(Long id) {
        Pessoa pessoa = repository.getOne(id);
        pessoa.setStatusDePagamento(!pessoa.isStatusDePagamento());
        repository.save(pessoa);
    }
}
