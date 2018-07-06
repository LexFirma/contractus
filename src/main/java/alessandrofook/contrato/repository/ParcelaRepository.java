package alessandrofook.contrato.repository;

import alessandrofook.contrato.model.contrato.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

}
