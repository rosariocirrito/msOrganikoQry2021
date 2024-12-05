package organiko.api.hs;

import java.util.List;

import organiko.api.hs.pl.IncaricoDTO;
import organiko.api.hs.pl.PersonaFisicaDTO;
import organiko.api.hs.pl.PersonaGiuridicaDTO;

public interface OrganikoQryService {
	// 
	IncaricoDTO findIncaricoById(Integer id);
	List<IncaricoDTO> findIncarichiApicaliByAssessoratoIDAndAnno(Integer dipartimentoID, int anno);
	// 1.1 trova gli incarichi di Dirigente Generale per quel Dipartimento e quell'anno
	List<IncaricoDTO> findIncarichiApicaliByDipartimentoIDAndAnno(Integer dipartimentoID, int anno);
	// 1.2 trova gli incarichi di Dirigente non Generale per quel Dipartimento e quell'anno
	List<IncaricoDTO> findIncarichiByDipartimentoIDAndAnno(Integer dipartimentoID, int anno);
	// 1.3
	List<IncaricoDTO> findIncarichiByStrutturaIDAndAnno(Integer strutturaID, int anno);
	List<IncaricoDTO> findIncarichiPopByStrutturaIDAndAnno(Integer strutturaID, int anno);
	// 2.1
	List<IncaricoDTO> findIncarichiByDirigenteIDAndAnno(Integer dirigenteID, int anno);
	
	//
	PersonaFisicaDTO findPersonaFisicaById(Integer id);
	List<PersonaFisicaDTO> findDipendentiStrictByStrutturaIDAndAnno(Integer idStruttura, int anno);
	List<PersonaFisicaDTO> findDipendentiGlobalByStrutturaIDAndAnno(Integer idStruttura, int anno);
	List<PersonaFisicaDTO> findDipendentiByDipartimentoIDAndAnno(Integer idDipartimento, int anno);
	List<PersonaFisicaDTO> findDipendentiByDipartimentoID(Integer idDipartimento);
	
	//-----------------------------------
	PersonaGiuridicaDTO findPersonaGiuridicaById(Integer id);
	PersonaGiuridicaDTO findDipartimentoByPersonaFisicaID(Integer pfID);
	PersonaGiuridicaDTO findDipartimentoByPersonaGiuridicaID(Integer pfID);
	PersonaGiuridicaDTO findAssessoratoByPersonaFisicaID(Integer pfID);
	
	//-----------------------------------
	List<PersonaGiuridicaDTO> listSubStruttureByDipartimentoIDAndAnno(Integer padreID, int anno);
	List<PersonaGiuridicaDTO> listAssessoratiAndAnno(int anno);
	List<PersonaGiuridicaDTO> listDipartimentiByAssessoratoIDAndAnno(Integer assID, int anno);
	List<PersonaGiuridicaDTO> findStruttureByStrutturaPadreIDAndAnno(Integer padreID, int anno);
}
