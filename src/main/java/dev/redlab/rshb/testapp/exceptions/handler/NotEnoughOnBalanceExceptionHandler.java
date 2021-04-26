package dev.redlab.rshb.testapp.exceptions.handler;

import dev.redlab.rshb.testapp.dto.response.ExceptionResponse;
import dev.redlab.rshb.testapp.exceptions.NotEnoughOnBalanceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class NotEnoughOnBalanceExceptionHandler {

    @ExceptionHandler(value = NotEnoughOnBalanceException.class)
    public ResponseEntity<ExceptionResponse> onException(Exception exception) {
        log.error("Error", exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .build());
    }

}
