package alessandrofook.contrato.controller;

import alessandrofook.contrato.service.PessoaService;
import alessandrofook.contrato.model.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    @ResponseBody
    public List<Pessoa> getPessoas() {
        return service.getPessoas();
    }

}
