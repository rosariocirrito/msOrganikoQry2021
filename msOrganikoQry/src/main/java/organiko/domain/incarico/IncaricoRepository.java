package organiko.domain.incarico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import organiko.domain.personaFisica.PersonaFisica;
import organiko.domain.personaGiuridica.PersonaGiuridica;


public interface IncaricoRepository extends JpaRepository<Incarico, Integer>{
	List<Incarico> findAllByPersonaFisica(PersonaFisica pf);
	List<Incarico> findAllByPersonaGiuridica(PersonaGiuridica pg);
}
