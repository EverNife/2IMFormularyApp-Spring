package br.com.finalcraft.im2.dto;

import br.com.finalcraft.im2.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {

    public ErrorCode errorCode;
    public String message;
    public HttpStatus status;
    public Map<String, String> extra;

}
