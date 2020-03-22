package pl.lostworld.lostworldbackend.jsonTemplates;

import lombok.Getter;
import lombok.Setter;
import pl.lostworld.lostworldbackend.internalTemplates.Pair;

import java.util.*;

@Getter
@Setter
public class ResponseValidationFailedTemplate {
    private Date timestamp = new Date();
    private Integer status = 0;
    private List<Pair<String, String>> errors = new LinkedList<>();

    public ResponseValidationFailedTemplate(Integer status) {
        this.status = status;
    }

    public void addError(String field, String value) {
        errors.add(new Pair<>(field, value));
    }
}
