package organiko.domain.personaFisicaTipo;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("personaFisicaTipoService")
@Repository
@Transactional
public class PersonaFisicaTipoServiceImpl implements PersonaFisicaTipoService {
	
	private Log log = LogFactory.getLog(PersonaFisicaTipoServiceImpl.class);

	// vedi Spring Data Pro Spring 3 p380 al posto di em uso il Repository
	@Autowired 
	private PersonaFisicaTipoRepository repo;
	
	

	@Transactional(readOnly=true)
	public PersonaFisicaTipo findById(Integer id) {
		return repo.getOne(id);
	}

	public PersonaFisicaTipo save(PersonaFisicaTipo tipo) {
		if (tipo.getIdopPersonaFisicaTipo() == null) { // Insert 
			log.info("Inserting new OpPersonaFisicaTipo");
		} else {                       // Update 
			log.info("Updating existing OpPersonaFisicaTipo");
		}
		log.info("persona saved with id: " + tipo.getIdopPersonaFisicaTipo());
		return repo.save(tipo);
	}

	public void delete(PersonaFisicaTipo tipo) {
		log.info("OpPersonaFisicaTipo with id: " + tipo.getIdopPersonaFisicaTipo() + " deleted successfully");
		repo.delete(tipo);
		
	}

	
	
	//--------------------------------------------------------------------------------
	
	
	

}
