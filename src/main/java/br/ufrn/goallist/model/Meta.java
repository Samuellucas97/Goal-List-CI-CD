package br.ufrn.goallist.model;


import br.ufrn.goallist.enums.MetaEstado;
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
public class Meta extends BaseEntity {
    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataPrevisaoConclusao;
    private MetaEstado estado;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "meta_id")
    private List<Etapa> etapas;
    @ManyToOne(fetch = FetchType.LAZY)
    private Objetivo objetivo;
}
