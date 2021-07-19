package br.ufrn.goallist.controller.handler;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
class ExceptionalDetails {
    protected String title;
    protected Integer status;
    protected LocalDateTime timestamp;
    protected String developerMessage;
}