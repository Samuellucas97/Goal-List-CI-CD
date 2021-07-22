package br.ufrn.goallist.model;


import br.ufrn.goallist.enums.MetaEstado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3913629036907715099L;
    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataPrevisaoConclusao;
    private LocalDate dataConclusao;
    private MetaEstado estado;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "meta_id")
    private List<Etapa> etapas;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa pessoa;
}
