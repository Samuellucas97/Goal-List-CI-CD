package br.ufrn.goallist.controller;

import br.ufrn.goallist.controller.dto.MetaDto;
import br.ufrn.goallist.model.Meta;
import br.ufrn.goallist.service.IMetaService;
import br.ufrn.goallist.exception.CustomException;
import br.ufrn.goallist.service.impl.DefaultMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/meta")
public class MetaController {
    private final IMetaService metaService;

    @Autowired
    public MetaController(DefaultMetaService metaService) {
        this.metaService = metaService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Meta> getAll() {
        return metaService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Meta create(@RequestBody MetaDto meta) {
        return metaService.create(MetaDto.transform(meta));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Meta update(@PathVariable Long id, @RequestBody MetaDto meta) throws CustomException {
        return metaService.update(id, MetaDto.transform(meta));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) throws CustomException {
        metaService.deleteById(id);
    }
}
