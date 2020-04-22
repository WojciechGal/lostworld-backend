package pl.lostworld.lostworldbackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.lostworld.lostworldbackend.templates.Pair;

import java.util.*;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<Pair<String, String>> errors = new LinkedList<>();
        ex.getBindingResult().getFieldErrors().forEach((err) -> {
            errors.add(new Pair<>(err.getField(), err.getDefaultMessage()));
        });

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }
}
