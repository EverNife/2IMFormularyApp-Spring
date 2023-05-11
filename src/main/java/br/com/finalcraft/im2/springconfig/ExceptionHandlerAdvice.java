package br.com.finalcraft.im2.springconfig;

import br.com.finalcraft.im2.dto.ErrorResponseDTO;
import br.com.finalcraft.im2.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ErrorResponse.class)
    public ResponseEntity handleException(ErrorResponse e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponseDTO(
                        e.getErrorCode(),
                        e.getMessage(),
                        e.getHttpStatus(),
                        e.getExtraData()
                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
