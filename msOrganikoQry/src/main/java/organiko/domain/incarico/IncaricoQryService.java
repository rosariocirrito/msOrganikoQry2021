package organiko.domain.incarico;

import java.util.List;

public interface IncaricoQryService {
	// Find a contact with details by id
		public Incarico findById(Integer id);
		
		List<Incarico> findIncarichiApicaliByAssessoratoIDAndAnno(Integer assessoratoID, int anno);
		// 
		//1.1 solo gli apicali
		List<Incarico> findIncarichiApicaliByDipartimentoIDAndAnno(Integer dipartimentoID, int anno);
		
		//1.2 comprende gli incarichi delle sottostrutture 
		List<Incarico> findIncarichiByDipartimentoIDAndAnno(Integer dipartimentoID, int anno);
		
		//1.3
		// solo gli incarichi della struttura
		List<Incarico> findIncarichiByStrutturaIDAndAnno(Integer strutturaID, int anno);
		List<Incarico> findIncarichiPopByStrutturaIDAndAnno(Integer strutturaID, int anno);
		// 2.1
		// solo gli incarichi della persona
		List<Incarico> findIncarichiByDirigenteIDAndAnno(Integer dirigenteID, int anno);
}
