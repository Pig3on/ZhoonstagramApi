package vua.pavic.ZhoonstagramApi.security.errors;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ApiError {
    private String message;


    public ApiError(HttpStatus status) {
        message = status.toString();
    }
}
