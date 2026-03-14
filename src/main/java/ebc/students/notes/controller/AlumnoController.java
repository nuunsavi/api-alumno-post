package ebc.students.notes.controller;

import ebc.students.notes.dto.AlumnoRequestDTO;
import ebc.students.notes.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import ebc.students.notes.domain.Alumno;

@RestController
@RequestMapping("/alumnos")
@Slf4j
public class AlumnoController {

  @Autowired
  AlumnoService service;

  @PostMapping("")
  public ResponseEntity<String> crearAlumno(@RequestBody AlumnoRequestDTO alumno) {
    log.info("Recibiendo petición para crear al alumno: {}", alumno.nombres());
    try {
      Alumno student = service.registrarAlumno(alumno);
      if(student != null){
        log.info("Se guardo el alumno exitosamente");
        return ResponseEntity.status(201).body("Alumno registrado exitosamente");
      }else{
        log.info("No se puede guardar alumno con DNI duplicado");
        return ResponseEntity.status(201).body("El alumno con DNI %s ya ha sido registrado anteriormente".formatted(alumno.dni()));
      }
    } catch (Exception e) {
      log.error("Hubo un error al guardar el alumno", e);
      return ResponseEntity.status(500).build();
    }
  }
}