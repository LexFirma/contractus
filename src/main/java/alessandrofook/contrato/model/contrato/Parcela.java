package alessandrofook.contrato.model.contrato;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Parcela {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @DecimalMin(value = "0.01", message = "O total do contrato não pode ser menor ou igual a zero!")
  private double valor;

  @NotNull(message = "O vencimento não pode ser null!")
  @NotEmpty(message = "A data de vencimento não pode ser vazia!")
  private String vencimento;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public String getVencimento() {
    return vencimento;
  }

  public void setVencimento(String vencimento) {
    this.vencimento = vencimento;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Parcela)) {
      return false;
    }
    Parcela parcela = (Parcela) o;
    return Double.compare(parcela.getValor(), getValor()) == 0 &&
        Objects.equals(getId(), parcela.getId()) &&
        Objects.equals(getVencimento(), parcela.getVencimento());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId(), getValor(), getVencimento());
  }
}
