package pl.lostworld.lostworldbackend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ResponseUtils {

    public static <T> ResponseEntity<?> designCreatedResponse(T createdObject) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.CREATED.value());
        body.put("object", createdObject);

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
}
