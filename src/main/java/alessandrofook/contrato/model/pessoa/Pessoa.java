package alessandrofook.contrato.model.pessoa;

import alessandrofook.contrato.model.contrato.Contrato;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "O nome da pessoa não pode ser null!")
  @NotEmpty(message = "O nome da pessoa não pode ser vazio!")
  private String nome;

  private boolean statusDePagamento;

  @OneToMany
  private List<Contrato> contratos;

  public Pessoa() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public boolean isStatusDePagamento() {
    return statusDePagamento;
  }

  public void setStatusDePagamento(boolean statusDePagamento) {
    this.statusDePagamento = statusDePagamento;
  }

  public List<Contrato> getContratos() {
    return contratos;
  }

  public void setContratos(List<Contrato> contratos) {
    this.contratos = contratos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pessoa)) {
      return false;
    }
    Pessoa pessoa = (Pessoa) o;
    return Objects.equals(getId(), pessoa.getId())
        && Objects.equals(getNome(), pessoa.getNome());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getNome());
  }
}
