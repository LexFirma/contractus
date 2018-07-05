package alessandrofook.contrato.model.autenticacao;

import alessandrofook.contrato.model.pessoa.Pessoa;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Credencial {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "O login não pode ser null!")
  @NotEmpty(message = "O login não pode ser vazio!")
  @Column(unique = true)
  private String login;

  @NotNull(message = "A senha não pode ser null!")
  @NotEmpty(message = "A senha não pode ser vazio!")
  private String senha;

  @NotNull(message = "A função da pessoa não pode ser null!")
  private Role role;

  @OneToOne
  private Pessoa pessoa;

  /**
   * Contrutor de credencial a partir de uma pessoa cadastrada no sistema.
   * @param pessoa - Objeto referente a uma pessoa cadastrada no sistema.
   */
  public Credencial(Pessoa pessoa) {
    this.setLogin(pessoa.getNome());
    this.setSenha("admin");
    this.setRole(Role.USUARIO);
    this.setPessoa(pessoa);
  }

  public Credencial() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Pessoa getPessoa() {
    return pessoa;
  }

  public void setPessoa(Pessoa pessoa) {
    this.pessoa = pessoa;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Credencial)) {
      return false;
    }
    Credencial that = (Credencial) o;
    return Objects.equals(getLogin(), that.getLogin())
        && Objects.equals(getSenha(), that.getSenha());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getLogin(), getSenha());
  }
}
