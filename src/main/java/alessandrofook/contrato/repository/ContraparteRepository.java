package alessandrofook.contrato.repository;

import alessandrofook.contrato.model.contrato.Contraparte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContraparteRepository extends JpaRepository<Contraparte, Long> {

  Contraparte findByRegistro(String registro);

  boolean existsByRegistro(String registro);
}
