package alessandrofook.contrato.model.contrato;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Contraparte {

  @Id
  @NotEmpty(message = "O registro da contraparte não pode ser vazio!")
  @NotNull(message = "O registro da contraparte não pode ser null!")
  @Column(unique = true)
  private String registro;

  @NotEmpty(message = "O nome da contraparte não pode ser vazio!")
  @NotNull(message = "O nome da contraparte não pode ser null!")
  private String nome;

  @NotEmpty(message = "O endereço da contraparte não pode ser vazio!")
  @NotNull(message = "O endereço da contraparte não pode ser null!")
  private String endereco;

  @NotEmpty(message = "O contato da contraparte não pode ser vazio!")
  @NotNull(message = "O contato da contraparte não pode ser null!")
  private String contato;

  @NotNull(message = "O tipo de registro da contraparte não pode ser null!")
  private TipoDeRegistro tipoDeRegistro;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getContato() {
    return contato;
  }

  public void setContato(String contato) {
    this.contato = contato;
  }

  public String getRegistro() {
    return registro;
  }

  public void setRegistro(String registro) {
    this.registro = registro;
  }

  public TipoDeRegistro getTipoDeRegistro() {
    return tipoDeRegistro;
  }

  public void setTipoDeRegistro(TipoDeRegistro tipoDeRegistro) {
    this.tipoDeRegistro = tipoDeRegistro;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Contraparte)) {
      return false;
    }
    Contraparte that = (Contraparte) o;
    return Objects.equals(getRegistro(), that.getRegistro())
        && Objects.equals(getNome(), that.getNome())
        && Objects.equals(getEndereco(), that.getEndereco())
        && Objects.equals(getContato(), that.getContato())
        && getTipoDeRegistro() == that.getTipoDeRegistro();
  }

  @Override
  public int hashCode() {

    return Objects.hash(getRegistro(), getNome(), getEndereco(), getContato(), getTipoDeRegistro());
  }
}
