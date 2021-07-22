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
    private LocalDateTime updatedAt;

    public void create() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    public void update() {
        updatedAt = LocalDateTime.now();
    }
}
