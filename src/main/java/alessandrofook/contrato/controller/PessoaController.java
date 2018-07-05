package alessandrofook.contrato.controller;

import alessandrofook.contrato.model.pessoa.Pessoa;
import alessandrofook.contrato.service.CredencialService;
import alessandrofook.contrato.service.PessoaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {

  @Autowired
  private PessoaService pessoaService;

  @Autowired
  private CredencialService credencialService;

  /**
   * Requisição responsável por cadastar uma pessoa e gerar sua respectiva credencial de acesso ao
   * sistema, fazendo as devidas conexões e relações na base de dados.
   * @param pessoa - Objeto contendo as informações da pessoa a serem cadastradas no sistema.
   * @return Um objeto do tipo Pessoa conforme cadastrado no sistema.
   */
  @PostMapping
  @ResponseBody
  @Transactional
  public Pessoa cadastrarPessoa(@RequestBody Pessoa pessoa) {

    Pessoa pessoaCadastrada = pessoaService.cadastrarPessoa(pessoa);
    credencialService.gerarCredencialDePessoa(pessoaCadastrada);

    return pessoaCadastrada;
  }

  @PatchMapping("{id}")
  @ResponseBody
  public Pessoa mudarStatusDePagamento(@PathVariable("id") Long id) {
    return pessoaService.mudarStatusDePagamento(id);
  }

  @PutMapping
  @ResponseBody
  public Pessoa editarPessoa(@RequestBody Pessoa pessoa) {
    return pessoaService.editarPessoa(pessoa);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public void deletarPessoa(@PathVariable("id") Long id) {
    credencialService.removerCredencialDePessoa(id);
    pessoaService.removerPessoa(id);
  }

  @GetMapping
  @ResponseBody
  public List<Pessoa> listarPessoas() {
    return pessoaService.listarPessoas();
  }


}
