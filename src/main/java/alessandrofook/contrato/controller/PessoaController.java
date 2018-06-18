package alessandrofook.contrato.controller;

import alessandrofook.contrato.model.PessoaService;
import alessandrofook.contrato.model.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    @ResponseBody
    public Pessoa cadastrarPessoa(@RequestBody Pessoa pessoa) {
        return service.cadastrarPessoa(pessoa);
    }

}
