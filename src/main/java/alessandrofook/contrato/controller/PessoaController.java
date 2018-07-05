package alessandrofook.contrato.controller;

import alessandrofook.contrato.model.pessoa.Pessoa;
import alessandrofook.contrato.service.PessoaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {

  @Autowired
  private PessoaService service;

  @PostMapping
  @ResponseBody
  public Pessoa cadastrarPessoa(@RequestBody Pessoa pessoa) {
    return service.cadastrarPessoa(pessoa);
  }

  @PostMapping("{id}")
  @ResponseBody
  public Pessoa mudarStatusDePagamento(@PathVariable("id") Long id) {
    return service.mudarStatusDePagamento(id);
  }

  @DeleteMapping("/{id}")
  public void deletarPessoa(@PathVariable("id") Long id) {
    service.removerPessoa(id);
  }

  @GetMapping
  @ResponseBody
  public List<Pessoa> listarPessoas() {
    return service.listarPessoas();
  }
}
