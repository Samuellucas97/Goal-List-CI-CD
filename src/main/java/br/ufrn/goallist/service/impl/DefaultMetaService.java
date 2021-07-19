package br.ufrn.goallist.service.impl;

import br.ufrn.goallist.model.Meta;
import br.ufrn.goallist.repository.MetaRepository;
import br.ufrn.goallist.service.IMetaService;
import br.ufrn.goallist.exception.MetaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMetaService implements IMetaService {
    private final MetaRepository metaRepository;

    @Override
    public List<Meta> getAll() {
        return metaRepository.findAll();
    }

    @Override
    public Meta create(Meta meta) {
        meta.create();
        return metaRepository.save(meta);
    }

    @Override
    public Meta update(Long id, Meta meta) throws MetaNotFoundException {
        Meta metaAntiga = findMetaOrThrowsException(id);
        metaAntiga.update();
        return metaRepository.save(metaAntiga);
    }

    @Override
    public void deleteById(Long id) throws MetaNotFoundException {
        Meta meta = findMetaOrThrowsException(id);
        metaRepository.delete(meta);
    }

    private Meta findMetaOrThrowsException(Long id) throws MetaNotFoundException {
        return metaRepository.findById(id).orElseThrow(MetaNotFoundException::new);
    }
}
