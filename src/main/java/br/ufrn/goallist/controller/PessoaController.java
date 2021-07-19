package br.ufrn.goallist.controller;

import br.ufrn.goallist.controller.dto.PessoaDto;
import br.ufrn.goallist.model.Pessoa;
import br.ufrn.goallist.service.IPessoaService;
import br.ufrn.goallist.exception.CustomException;
import br.ufrn.goallist.service.impl.DefaultPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/pessoa")
public class PessoaController {
    private final IPessoaService pessoaService;

    @Autowired
    public PessoaController(DefaultPessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Pessoa> getAll() {
        return pessoaService.getAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Pessoa create(@RequestBody PessoaDto pessoa) {
        return pessoaService.create(PessoaDto.transform(pessoa));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Pessoa update(@PathVariable Long id, @RequestBody PessoaDto pessoa) throws CustomException {
        return pessoaService.update(id, PessoaDto.transform(pessoa));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) throws CustomException {
        pessoaService.deleteById(id);
    }
}
