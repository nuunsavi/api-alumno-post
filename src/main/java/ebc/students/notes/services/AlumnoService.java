package ebc.students.notes.services;

import ebc.students.notes.domain.Alumno;
import ebc.students.notes.dto.AlumnoRequestDTO;
import ebc.students.notes.repository.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AlumnoService {

  @Autowired
  AlumnoRepository repository;

  public Alumno registrarAlumno(AlumnoRequestDTO dto) {
    Alumno exists =  repository.findByDni(dto.dni());
    if(exists == null){
      log.info("Calculando promedio del alumno con DNI ", dto.dni());
      double promedioCalculado = dto.notas().stream()
        .mapToInt(Integer::intValue)
        .average()
        .orElse(0.0);
      Alumno nuevoAlumno = new Alumno(
        dto.dni(),
        dto.nombres(),
        dto.notas(),
        promedioCalculado
      );
      log.debug("Realizando persistencia del alumno con promedio de ", promedioCalculado);
      Alumno savedAlumno = repository.save(nuevoAlumno);
      log.debug("Alumno {} guardado existosamente", savedAlumno.getNombres());
      return savedAlumno;
    }
    return null;
  }
}