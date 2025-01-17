package organiko.domain.personaGiuridicaTipo;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("personaGiuridicaTipoService")
@Repository
@Transactional
public class PersonaGiuridicaTipoServiceImpl implements PersonaGiuridicaTipoService {
	
	private Log log = LogFactory.getLog(PersonaGiuridicaTipoServiceImpl.class);

	// vedi Spring Data Pro Spring 3 p380 al posto di em uso il Repository
	@Autowired 
	private PersonaGiuridicaTipoRepository repo;
	
	

	@Transactional(readOnly=true)
	public PersonaGiuridicaTipo findById(Integer id) {
		return repo.getOne(id);
	}

	public PersonaGiuridicaTipo save(PersonaGiuridicaTipo tipo) {
		if (tipo.getIdopPersonaGiuridicaTipo() == null) { // Insert 
			log.info("Inserting new OpPersonaGiuridicaTipo");
		} else {                       // Update 
			log.info("Updating existing OpPersonaGiuridicaTipo");
		}
		log.info("persona saved with id: " + tipo.getIdopPersonaGiuridicaTipo());
		return repo.save(tipo);
	}

	public void delete(PersonaGiuridicaTipo tipo) {
		log.info("OpPersonaGiuridicaTipo with id: " + tipo.getIdopPersonaGiuridicaTipo() + " deleted successfully");
		repo.delete(tipo);
		
	}

	
	
	//--------------------------------------------------------------------------------
	
	
	

}
