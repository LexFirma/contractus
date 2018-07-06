package alessandrofook.contrato.model.contrato;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
public class Contrato {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @DecimalMin(value = "0.01", message = "O total do contrato não pode ser menor ou igual a zero!")
  private double total;

  @NotNull(message = "O Usuário deve ter um papelDoUsuario definido no contrato!")
  private Papel papelDoUsuario;

  @OneToMany
  private List<Contraparte> contrapartes;

  @OneToMany
  private List<Parcela> parcelas;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Papel getPapelDoUsuario() {
    return papelDoUsuario;
  }

  public void setPapelDoUsuario(Papel papelDoUsuario) {
    this.papelDoUsuario = papelDoUsuario;
  }

  public List<Contraparte> getContrapartes() {
    return contrapartes;
  }

  public void setContrapartes(
      List<Contraparte> contrapartes) {
    this.contrapartes = contrapartes;
  }

  public List<Parcela> getParcelas() {
    return parcelas;
  }

  public void setParcelas(List<Parcela> parcelas) {
    this.parcelas = parcelas;
  }


}
