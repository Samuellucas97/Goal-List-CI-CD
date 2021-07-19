package br.ufrn.goallist.controller.handler;

import br.ufrn.goallist.exception.CustomException;
import br.ufrn.goallist.exception.EtapaNotFoundException;
import br.ufrn.goallist.exception.PessoaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {EtapaNotFoundException.class, PessoaNotFoundException.class})
    public ResponseEntity<ResourceNotFoundDetails> handlerResourceNotFoundException(CustomException exception) {
        return new ResponseEntity<>(
                ResourceNotFoundDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Resource not found")
                        .developerMessage(exception.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
//
//        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
//        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
//
//        return new ResponseEntity<>(
//                ValidationExceptionDetails.builder()
//                        .timestamp(LocalDateTime.now())
//                        .status(HttpStatus.BAD_REQUEST.value())
//                        .title("Field Validation error")
//                        .detail("Check the field(s) bellow")
//                        .developerMessage(ex.getClass().getName())
//                        .fields(fields)
//                        .fieldsMessage(fieldsMessage)
//                        .build(), HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(
//            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//
//        ExceptionalDetails exceptionalDetails =  ExceptionalDetails.builder()
//                .timestamp(LocalDateTime.now())
//                .status(status.value())
//                .title(ex.getCause().getMessage())
//                .detail(ex.getMessage())
//                .developerMessage(ex.getClass().getName())
//                .build();
//
//
//        return  new ResponseEntity<>(exceptionalDetails, headers, status);
//    }
}

