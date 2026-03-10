package ebc.students.notes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record AlumnoRequestDTO(
    @JsonProperty("DNI")
    String dni,
    String nombres,
    List<Integer> notas
) {}