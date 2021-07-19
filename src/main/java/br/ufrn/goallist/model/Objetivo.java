package br.ufrn.goallist.model;


import br.ufrn.goallist.enums.ObjetivoEstado;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
public class Objetivo extends BaseEntity {
    private String titulo;
    private String descricao;
    private LocalDate dataConclusao;
    private ObjetivoEstado estado;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "objetivo_id")
    private List<Meta> metas;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa pessoa;
}
