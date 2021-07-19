package br.ufrn.goallist.controller;

import br.ufrn.goallist.controller.dto.ObjetivoDto;
import br.ufrn.goallist.model.Objetivo;
import br.ufrn.goallist.service.IObjetivoService;
import br.ufrn.goallist.service.exception.CustomException;
import br.ufrn.goallist.service.impl.DefaultObjetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/objetivo")
public class ObjetivoController {
    private final IObjetivoService objetivoService;

    @Autowired
    public ObjetivoController(DefaultObjetivoService objetivoService) {
        this.objetivoService = objetivoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Objetivo> getAllObjetivos() {
        return objetivoService.getAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public Objetivo createObjetivo(ObjetivoDto objetivo) {
        return objetivoService.create(ObjetivoDto.transform(objetivo));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public Objetivo updateObjetivo(@PathVariable Long id, @RequestBody ObjetivoDto objetivo) throws CustomException {
        return objetivoService.update(id, ObjetivoDto.transform(objetivo));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable Long id) throws CustomException {
        objetivoService.deleteById(id);
    }
}
