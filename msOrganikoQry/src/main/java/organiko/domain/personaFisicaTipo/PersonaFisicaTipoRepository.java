package organiko.domain.personaFisicaTipo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaFisicaTipoRepository extends JpaRepository<PersonaFisicaTipo, Integer> {
	PersonaFisicaTipo findByIdopPersonaFisicaTipo(Integer Id);
}
