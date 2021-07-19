package br.ufrn.goallist.repository;

import br.ufrn.goallist.model.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetivoRepository extends JpaRepository<Objetivo, Long> {
}
