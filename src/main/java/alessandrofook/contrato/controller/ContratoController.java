package alessandrofook.contrato.controller;

import alessandrofook.contrato.model.contrato.Contrato;
import alessandrofook.contrato.model.contrato.Parcela;
import alessandrofook.contrato.service.ContratoService;
import alessandrofook.contrato.service.PessoaService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

  /**
   * Chamada que realiza o cadastro de um novo contrato vinculando a respectiva pessoa que o
   * cadastrou no sistema.
   * @param contrato - Objeto do tipo contrato a ser cadastrado.
   * @param pessoaId - id da pessoa que será a titular do contrato.
   * @return - Objeto do tipo contrato conforme se encontra na base de dados.
   */
  @PostMapping("/{pessoaId}")
  @ResponseBody
  public Contrato cadastrarContrato(@RequestBody @Valid Contrato contrato,
                                    @PathVariable("pessoaId") Long pessoaId) {

    Contrato contratoCadastrado = contratoService.cadastrarContrato(contrato);
    pessoaService.cadastrarContrato(contratoCadastrado, pessoaId);

    return contratoCadastrado;
  }

  /**
   * Método que remove um contrato do sistema, atualizando a pessoa que era titular do contrato.
   * @param id - id do contrato a ser removido.
   */
  @DeleteMapping("/{id}")
  public void deletarContrato(@PathVariable("id") Long id) {

    Contrato contrato = contratoService.getContrato(id);

    pessoaService.removerContrato(contrato);
    contratoService.removerContrato(id);
  }

  @PatchMapping("/{parcelaId}")
  @ResponseBody
  public Parcela adimplirParcela(@PathVariable("parcelaId") Long parcelaId) {
    return contratoService.adimplirParcela(parcelaId);
  }
}
