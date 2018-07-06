package alessandrofook.contrato.repository;

import alessandrofook.contrato.model.contrato.Contraparte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContraparteRepository extends JpaRepository<Contraparte, String> {

}
