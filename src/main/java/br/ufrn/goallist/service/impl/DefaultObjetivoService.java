package br.ufrn.goallist.service.impl;

import br.ufrn.goallist.model.Objetivo;
import br.ufrn.goallist.repository.ObjetivoRepository;
import br.ufrn.goallist.service.IObjetivoService;
import br.ufrn.goallist.service.exception.ObjetivoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultObjetivoService implements IObjetivoService {
    private final ObjetivoRepository objetivoRepository;

    @Override
    public List<Objetivo> getAll() {
        return objetivoRepository.findAll();
    }

    @Override
    public Objetivo create(Objetivo objetivo) {
        objetivo.create();
        return objetivoRepository.save(objetivo);
    }

    @Override
    public Objetivo update(Long id, Objetivo objetivo) throws ObjetivoNotFoundException {
        Objetivo objetivoAntiga = findObjetivoOrThrowsException(id);
        objetivoAntiga.update();
        return objetivoRepository.save(objetivoAntiga);
    }

    @Override
    public void deleteById(Long id) throws ObjetivoNotFoundException {
        Objetivo objetivo = findObjetivoOrThrowsException(id);
        objetivoRepository.delete(objetivo);
    }

    private Objetivo findObjetivoOrThrowsException(Long id) throws ObjetivoNotFoundException {
        return objetivoRepository.findById(id).orElseThrow(ObjetivoNotFoundException::new);
    }
}
