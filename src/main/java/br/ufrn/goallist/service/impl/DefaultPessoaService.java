package br.ufrn.goallist.service.impl;

import br.ufrn.goallist.model.Meta;
import br.ufrn.goallist.model.Pessoa;
import br.ufrn.goallist.repository.PessoaRepository;
import br.ufrn.goallist.service.IPessoaService;
import br.ufrn.goallist.service.exception.MetaNotFoundException;
import br.ufrn.goallist.service.exception.PessoaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultPessoaService implements IPessoaService {
    private final PessoaRepository pessoaRepository;

    @Override
    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa create(Pessoa pessoa) {
        pessoa.create();
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa update(Long id, Pessoa pessoa) throws PessoaNotFoundException {
        Pessoa pessoaAntiga = findPessoaOrThrowsException(id);
        pessoaAntiga.update();
        return pessoaRepository.save(pessoaAntiga);
    }

    @Override
    public void deleteById(Long id) throws PessoaNotFoundException {
        Pessoa pessoa = findPessoaOrThrowsException(id);
        pessoaRepository.delete(pessoa);
    }

    private Pessoa findPessoaOrThrowsException(Long id) throws PessoaNotFoundException {
        return pessoaRepository.findById(id).orElseThrow(PessoaNotFoundException::new);
    }
}
