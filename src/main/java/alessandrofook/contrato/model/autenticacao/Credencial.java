package alessandrofook.contrato.model.autenticacao;

import alessandrofook.contrato.model.pessoa.Pessoa;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Credencial implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "O username não pode ser null!")
  @NotEmpty(message = "O username não pode ser vazio!")
  @Column(unique = true)
  private String username;

  @NotNull(message = "A password não pode ser null!")
  @NotEmpty(message = "A password não pode ser vazio!")
  private String password;

  @NotNull(message = "A função da pessoa não pode ser null!")
  private Role role;

  @OneToOne
  private Pessoa pessoa;

  /**
   * Construtor de credencial a partir de uma pessoa cadastrada no sistema.
   * @param pessoa - Objeto referente a uma pessoa cadastrada no sistema.
   */
  public Credencial(Pessoa pessoa) {
    this.setUsername(pessoa.getNome());
    this.setPassword("admin");
    this.setRole(Role.ROLE_USUARIO);
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

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
    return Objects.equals(getUsername(), that.getUsername())
        && Objects.equals(getPassword(), that.getPassword());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getUsername(), getPassword());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

}
