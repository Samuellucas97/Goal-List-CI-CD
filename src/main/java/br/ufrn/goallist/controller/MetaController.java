package br.ufrn.goallist.controller;

import br.ufrn.goallist.controller.dto.EtapaDto;
import br.ufrn.goallist.controller.dto.MetaDto;
import br.ufrn.goallist.model.Etapa;
import br.ufrn.goallist.model.Meta;
import br.ufrn.goallist.service.IEtapaService;
import br.ufrn.goallist.service.IMetaService;
import br.ufrn.goallist.service.exception.CustomException;
import br.ufrn.goallist.service.impl.DefaultEtapaService;
import br.ufrn.goallist.service.impl.DefaultMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/meta")
public class MetaController {
    private final IMetaService metaService;

    @Autowired
    public MetaController(DefaultMetaService metaService) {
        this.metaService = metaService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Meta> getAll() {
        return metaService.getAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public Meta create(@RequestBody MetaDto meta) {
        return metaService.create(MetaDto.transform(meta));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public Meta update(@PathVariable Long id, @RequestBody MetaDto meta) throws CustomException {
        return metaService.update(id, MetaDto.transform(meta));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) throws CustomException {
        metaService.deleteById(id);
    }
}
