package organiko.domain.personaGiuridica;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import organiko.domain.personaGiuridicaTipo.PersonaGiuridicaTipo;

public interface PersonaGiuridicaRepository extends JpaRepository<PersonaGiuridica, Integer> {
	List<PersonaGiuridica> findByOpPersonaGiuridicaTipo(PersonaGiuridicaTipo tipo);
	List<PersonaGiuridica> findByOpPersonaGiuridica(PersonaGiuridica padre);
}
