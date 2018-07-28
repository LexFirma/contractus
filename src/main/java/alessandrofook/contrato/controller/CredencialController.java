package alessandrofook.contrato.controller;

import alessandrofook.contrato.model.autenticacao.Credencial;
import alessandrofook.contrato.service.CredencialService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credenciais")
public class CredencialController {

  @Autowired
  private CredencialService credencialService;

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public void criarAdministrador(@RequestBody @Valid Credencial credencial) {
    credencialService.cadastrarCredencial(credencial);
  }

  @PutMapping("/{login}")
  public void editarCredencial(@RequestBody @Valid Credencial credencial,
                               @PathVariable String login) {
    credencialService.editarCredencial(credencial, login);
  }

  @DeleteMapping("/{login}")
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public void removerCredencialDeAdmin(@PathVariable String login) {
    credencialService.removerCredencialDeAdmin(login);
  }

}
