package ebc.students.notes.services;

import ebc.students.notes.domain.Alumno;
import ebc.students.notes.dto.AlumnoRequestDTO;
import ebc.students.notes.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {

    private final AlumnoRepository repository;

    public AlumnoService(AlumnoRepository repository) {
        this.repository = repository;
    }

    public void registrarAlumno(AlumnoRequestDTO dto) {
        // Mapeo simple de DTO a Entidad
        Alumno nuevoAlumno = new Alumno(
            dto.dni(),
            dto.nombres(),
            dto.notas()
        );
        repository.save(nuevoAlumno);
    }
}