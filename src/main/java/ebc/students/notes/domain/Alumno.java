package ebc.students.notes.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dni", nullable = false, unique = true, length = 8)
    private String dni;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @ElementCollection
    @CollectionTable(name = "alumno_notas", joinColumns = @JoinColumn(name = "alumno_id"))
    @Column(name = "nota")
    private List<Integer> notas;

    @Column(name = "promedio", nullable = false)
    private Double promedio;

    public Alumno() {}

    public Alumno(String dni, String nombres, List<Integer> notas, Double promedio) {
      this.dni = dni;
      this.nombres = nombres;
      this.notas = notas;
      this.promedio = promedio;
    }

}