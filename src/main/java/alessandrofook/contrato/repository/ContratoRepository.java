package alessandrofook.contrato.repository;

import alessandrofook.contrato.model.contrato.Contrato;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

  List<Contrato> findByContrapartesRegistro(String registro);
}
