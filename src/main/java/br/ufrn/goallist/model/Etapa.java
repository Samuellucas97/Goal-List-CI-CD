package br.ufrn.goallist.model;


import br.ufrn.goallist.enums.EtapaEstado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Etapa extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3913629036907715099L;
    private String titulo;
    private String descricao;
    private EtapaEstado estado;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Meta meta;
}
