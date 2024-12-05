package organiko.domain.personaFisica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import organiko.domain.personaFisicaTipo.PersonaFisicaTipo;
import organiko.domain.personaGiuridica.PersonaGiuridica;


public interface PersonaFisicaRepository extends JpaRepository<PersonaFisica, Integer> {	
	List<PersonaFisica> findByOpPersonaGiuridicaAndOpPersonaFisicaTipo(
		PersonaGiuridica struttura, PersonaFisicaTipo tipo);
	List<PersonaFisica> findByCognome(String cognome);
	List<PersonaFisica> findByMatricola(String matricola);
	List<PersonaFisica> findByCognomeAndNome(String cognome, String nome);
}
