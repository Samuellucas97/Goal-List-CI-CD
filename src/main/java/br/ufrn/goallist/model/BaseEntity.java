package br.ufrn.goallist.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updateddAt;

    public void create() {
        createdAt = LocalDateTime.now();
        updateddAt = createdAt;
    }

    public void update() {
        updateddAt = LocalDateTime.now();
    }
}
