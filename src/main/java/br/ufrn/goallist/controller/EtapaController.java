package br.ufrn.goallist.controller;

import br.ufrn.goallist.controller.dto.EtapaDto;
import br.ufrn.goallist.model.Etapa;
import br.ufrn.goallist.service.IEtapaService;
import br.ufrn.goallist.service.exception.CustomException;
import br.ufrn.goallist.service.impl.DefaultEtapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/etapa")
public class EtapaController {
    private final IEtapaService etapaService;

    @Autowired
    public EtapaController(DefaultEtapaService etapaService) {
        this.etapaService = etapaService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Etapa> getAll() {
        return etapaService.getAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public Etapa create(@RequestBody EtapaDto etapa) {
        return etapaService.create(EtapaDto.transform(etapa));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public Etapa update(@PathVariable Long id, @RequestBody EtapaDto etapa) throws CustomException {
        return etapaService.update(id, EtapaDto.transform(etapa));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) throws CustomException {
        etapaService.deleteById(id);
    }
}
