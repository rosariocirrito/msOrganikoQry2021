package organiko.domain.personaGiuridicaTipo;



import java.util.List;

public interface PersonaGiuridicaTipoService {
	
	// Find a contact with details by id
	PersonaGiuridicaTipo findById(Integer id);
	
	// Insert or update a contact	
	PersonaGiuridicaTipo save(PersonaGiuridicaTipo tipo);
	
	// Delete a contact	
	void delete(PersonaGiuridicaTipo tipo);
	
	//-----------------------------------
}
