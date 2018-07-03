package alessandrofook.contrato.repository;

import alessandrofook.contrato.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findByNome();
}
