package alessandrofook.contrato.model.contrato;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

  @ManyToMany
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

  /**
   * Atualiza do valor do atributo total, considerando que este é um double com apenas duas casas
   * decimais.
   * @param total - valor total do contrato.
   */
  public void setTotal(double total) {

    DecimalFormat df = new DecimalFormat("###.##");
    this.total = Double.valueOf(df.format(total));
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Contrato)) {
      return false;
    }
    Contrato contrato = (Contrato) o;
    return Double.compare(contrato.getTotal(), getTotal()) == 0
        && Objects.equals(getId(), contrato.getId())
        && getPapelDoUsuario() == contrato.getPapelDoUsuario()
        && Objects.equals(getContrapartes(), contrato.getContrapartes())
        && Objects.equals(getParcelas(), contrato.getParcelas());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId(), getTotal(), getPapelDoUsuario(), getContrapartes(), getParcelas());
  }
}
