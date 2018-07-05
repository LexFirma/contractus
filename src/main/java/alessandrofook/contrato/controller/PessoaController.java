package alessandrofook.contrato.controller;

import alessandrofook.contrato.service.PessoaService;
import alessandrofook.contrato.model.pessoa.Pessoa;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
