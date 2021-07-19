package br.ufrn.goallist.service;

import br.ufrn.goallist.model.BaseEntity;
import br.ufrn.goallist.model.Etapa;
import br.ufrn.goallist.model.Pessoa;
import br.ufrn.goallist.service.exception.CustomException;

import java.util.List;

public interface IService<T extends BaseEntity> {
    List<T> getAll();
    T create(T t);
    T update(Long id, T t) throws CustomException;
    void deleteById(Long id) throws CustomException;



}
