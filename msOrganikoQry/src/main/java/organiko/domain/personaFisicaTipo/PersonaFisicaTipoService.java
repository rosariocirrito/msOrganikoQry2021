package organiko.domain.personaFisicaTipo;


import java.util.List;

public interface PersonaFisicaTipoService {
	
	// Find a contact with details by id
	PersonaFisicaTipo findById(Integer id);
	
	// Insert or update a contact	
	PersonaFisicaTipo save(PersonaFisicaTipo tipo);
	
	// Delete a contact	
	void delete(PersonaFisicaTipo tipo);
	
	//-----------------------------------
}
