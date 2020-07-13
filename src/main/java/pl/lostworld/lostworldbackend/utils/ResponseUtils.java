package pl.lostworld.lostworldbackend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ResponseUtils {

    public static <T> ResponseEntity<?> designCreatedResponse(T createdObject) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.CREATED.value());

        if (Long.class.equals(createdObject.getClass())) {
            body.put("id", createdObject);
        } else {
            body.put("object", createdObject);
        }

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<?> designAcceptedResponse(T acceptedObject) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.ACCEPTED.value());
        body.put("object", acceptedObject);

        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }

    public static <T> ResponseEntity<?> designOkResponse(T response) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.OK.value());
        body.put("response", response);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public static <T> ResponseEntity<?> designBadRequestResponse(T response) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", response);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<?> designBadRequestSingletonResponse(String response) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", response);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<?> designIAmATeapotResponse(T response) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.I_AM_A_TEAPOT.value());
        body.put("message", response);

        return new ResponseEntity<>(body, HttpStatus.I_AM_A_TEAPOT);
    }
}
