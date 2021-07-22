package br.ufrn.goallist.model;


import br.ufrn.goallist.enums.EtapaEstado;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
public class Etapa extends BaseEntity {
    private String titulo;
    private String descricao;
    private EtapaEstado estado;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Meta meta;
}
