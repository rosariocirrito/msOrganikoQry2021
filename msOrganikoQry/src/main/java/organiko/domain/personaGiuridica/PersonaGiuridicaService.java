package organiko.domain.personaGiuridica;


import java.util.List;

public interface PersonaGiuridicaService {
		
		// Find a contact with details by id
		public PersonaGiuridica findById(Integer id);
		public PersonaGiuridica findDipartimentoByPersonaGiuridicaID(Integer pgID);
		
		//-----------------------------------
		List<PersonaGiuridica> listSubStruttureByDipartimentoIDAndAnno(Integer padreID, int anno);
		List<PersonaGiuridica> listAssessoratiAndAnno(int anno);
		List<PersonaGiuridica> listDipartimentiByAssessoratoIDAndAnno(Integer assID, int anno);
		List<PersonaGiuridica> findStruttureByStrutturaPadreIDAndAnno(PersonaGiuridica padre, int anno);
		/*
		List<OpPersonaGiuridica> listPadreConSubStrutture(OpPersonaGiuridica padre);
		
		List<OpPersonaGiuridica> listSubStruttureAttive(OpPersonaGiuridica padre);
		
		List<OpPersonaGiuridica> listSubStruttureAttiveAnno(OpPersonaGiuridica padre, int anno);
		List<OpPersonaGiuridica> listSubStruttureCancellate(OpPersonaGiuridica padre);
		List<OpPersonaGiuridica> listStruttureDept(OpPersonaGiuridica dipartimento);
		List<OpPersonaGiuridica> listStruttureAttiveDept(OpPersonaGiuridica dipartimento);
		
		
		*/
} // ---------
