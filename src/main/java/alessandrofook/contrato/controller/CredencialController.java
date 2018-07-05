package alessandrofook.contrato.controller;

import alessandrofook.contrato.model.autenticacao.Credencial;
import alessandrofook.contrato.service.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credencial")
public class CredencialController {

  @Autowired
  private CredencialService credencialService;

  @PostMapping
  public void criarAdministrador(@RequestBody Credencial credencial) {
    credencialService.cadastrarCredencial(credencial);
  }

  @PutMapping
  public void editarCredencial(@RequestBody Credencial credencial) {
    credencialService.editarCredencial(credencial);
  }

  @DeleteMapping("/{login}")
  @Transactional
  public void removerCredencialDeAdmin(@PathVariable String login) {
    credencialService.removerCredencialDeAdmin(login);
  }

}
