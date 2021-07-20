package br.ufrn.goallist.service.impl;

import br.ufrn.goallist.enums.EtapaEstado;
import br.ufrn.goallist.model.Etapa;
import br.ufrn.goallist.repository.EtapaRepository;
import br.ufrn.goallist.service.IEtapaService;
import br.ufrn.goallist.exception.EtapaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultEtapaService implements IEtapaService {
    private final EtapaRepository etapaRepository;

    @Override
    public List<Etapa> getAll() {
        return etapaRepository.findAll();
    }

    @Override
    public Etapa create(Etapa etapa) {
        etapa.create();
        return etapaRepository.save(etapa);
    }

    @Override
    public Etapa update(Long id, Etapa etapa) throws EtapaNotFoundException {
        Etapa etapaAntiga = findEtapaOrThrowsException(id);
        etapaAntiga.setTitulo(etapa.getTitulo());
        etapaAntiga.setDescricao(etapa.getDescricao());
        etapaAntiga.setEstado(etapa.getEstado());
        etapaAntiga.setMeta(etapa.getMeta());
        etapaAntiga.update();
        return etapaRepository.save(etapaAntiga);
    }

    @Override
    public void deleteById(Long id) throws EtapaNotFoundException {
        Etapa etapa = findEtapaOrThrowsException(id);
        etapaRepository.delete(etapa);
    }

    private Etapa findEtapaOrThrowsException(Long id) throws EtapaNotFoundException {
        return etapaRepository.findById(id).orElseThrow(EtapaNotFoundException::new);
    }
}
