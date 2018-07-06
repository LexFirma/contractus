package alessandrofook.contrato.repository;

import alessandrofook.contrato.model.contrato.Contrato;
import alessandrofook.contrato.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

  boolean existsByNome(String nome);

  Pessoa findByContratosContaining(Contrato contrato);
}
