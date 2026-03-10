package ebc.students.notes.controller;

import ebc.students.notes.dto.AlumnoRequestDTO;
import ebc.students.notes.services.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/alumnos")
public class AlumnoController {

    private final AlumnoService service;

    public AlumnoController(AlumnoService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<String> crearAlumno(@RequestBody AlumnoRequestDTO request) {
        service.registrarAlumno(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Alumno registrado exitosamente");
    }
}