package alessandrofook.contrato.repository;

import alessandrofook.contrato.model.pessoa.FuncaoPessoa;
import alessandrofook.contrato.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByNome(String nome);
}
