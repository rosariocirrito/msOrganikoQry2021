package organiko.domain.personaFisica;


import java.util.List;

public interface PersonaFisicaService {
	PersonaFisica findById(Integer id);
	List<PersonaFisica> findDipendentiStrictOfStructureIdAndAnno(Integer strutturaId,int anno);
	List<PersonaFisica> findDipendentiGlobalOfStructureIdAndAnno(Integer strutturaId,int anno);
	List<PersonaFisica> findDipendentiByDipartimentoIDAndAnno(Integer strutturaId,int anno);
	List<PersonaFisica> findDipendentiByDipartimentoID(Integer strutturaId);
}
