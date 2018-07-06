package alessandrofook.contrato.controller;

import alessandrofook.contrato.model.contrato.Contrato;
import alessandrofook.contrato.model.pessoa.Pessoa;
import alessandrofook.contrato.service.ContratoService;
import alessandrofook.contrato.service.PessoaService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

  @Autowired
  private ContratoService contratoService;

  @Autowired
  private PessoaService pessoaService;

  @PostMapping("/{pessoaId}")
  @ResponseBody
  public Contrato cadastrarContrato(@RequestBody @Valid Contrato contrato, @PathVariable("pessoaId") Long pessoaId) {

    Contrato contratoCadastrado = contratoService.cadastrarContrato(contrato);
    Pessoa pessoa = pessoaService.getPessoa(pessoaId);

    pessoa.getContratos().add(contratoCadastrado);
    pessoaService.editarPessoa(pessoa);

    return contratoCadastrado;
  }

}
