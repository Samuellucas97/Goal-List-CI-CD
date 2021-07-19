package br.ufrn.goallist.controller;

import br.ufrn.goallist.controller.dto.PessoaDto;
import br.ufrn.goallist.model.Pessoa;
import br.ufrn.goallist.service.IPessoaService;
import br.ufrn.goallist.service.exception.CustomException;
import br.ufrn.goallist.service.impl.DefaultPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pessoa")
public class PessoaController {
    private final IPessoaService pessoaService;

    @Autowired
    public PessoaController(DefaultPessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Pessoa> getAllPessoas() {
        return pessoaService.getAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public Pessoa createPessoa(PessoaDto pessoa) {
        return pessoaService.create(PessoaDto.transform(pessoa));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public Pessoa updatePessoa(@PathVariable Long id, @RequestBody PessoaDto pessoa) throws CustomException {
        return pessoaService.update(id, PessoaDto.transform(pessoa));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable Long id) throws CustomException {
        pessoaService.deleteById(id);
    }
}
