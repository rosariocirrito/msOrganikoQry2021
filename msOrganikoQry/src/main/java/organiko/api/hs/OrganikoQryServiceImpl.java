package organiko.api.hs;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import organiko.api.hs.pl.IncaricoDTO;
import organiko.api.hs.pl.PersonaFisicaDTO;
import organiko.api.hs.pl.PersonaGiuridicaDTO;
import organiko.domain.incarico.Incarico;
import organiko.domain.incarico.IncaricoQryService;
import organiko.domain.personaFisica.PersonaFisica;
import organiko.domain.personaFisica.PersonaFisicaService;
import organiko.domain.personaGiuridica.PersonaGiuridica;
import organiko.domain.personaGiuridica.PersonaGiuridicaService;


@Service("organikoQryService")
public class OrganikoQryServiceImpl implements OrganikoQryService {
	
	private Log log = LogFactory.getLog(OrganikoQryServiceImpl.class);

	// vedi Spring Data Pro Spring 3 p380 al posto di em uso il Repository
	@Autowired 
	private IncaricoQryService incServizi;
	@Autowired 
	private PersonaFisicaService pfServizi;
	@Autowired 
	private PersonaGiuridicaService pgServizi;

	// Incarichi
	//  
	@Override
	public IncaricoDTO findIncaricoById(Integer id) {
		IncaricoDTO incDTO = new IncaricoDTO(incServizi.findById(id));
		return incDTO;
	}
	
	// 1.1 trova gli incarichi di Dirigente Generale per quel Dipartimento e quell'anno
	@Override
	public List<IncaricoDTO> findIncarichiApicaliByAssessoratoIDAndAnno(Integer assessoratoID, int anno) {
			List<Incarico> lstIncarichi = incServizi.findIncarichiApicaliByAssessoratoIDAndAnno(assessoratoID, anno);
			//System.out.println("OrganikoQryServiceImpl.findIncarichiApicaliByDipartimentoIDAndAnno() lstIncarichi("+dipartimentoID+","+anno+") empty? = "+lstIncarichi.isEmpty());
			List<IncaricoDTO> lstIncarichiDTO = new ArrayList<IncaricoDTO>();
			for (Incarico inc : lstIncarichi){
				IncaricoDTO incDTO = new IncaricoDTO(inc);
				lstIncarichiDTO.add(incDTO);
			}
			return lstIncarichiDTO;
		}
		
	// 1.1 trova gli incarichi di Dirigente Generale per quel Dipartimento e quell'anno
	@Override
	public List<IncaricoDTO> findIncarichiApicaliByDipartimentoIDAndAnno(Integer dipartimentoID, int anno) {
		List<Incarico> lstIncarichi = incServizi.findIncarichiApicaliByDipartimentoIDAndAnno(dipartimentoID, anno);
		//System.out.println("OrganikoQryServiceImpl.findIncarichiApicaliByDipartimentoIDAndAnno() lstIncarichi("+dipartimentoID+","+anno+") empty? = "+lstIncarichi.isEmpty());
		List<IncaricoDTO> lstIncarichiDTO = new ArrayList<IncaricoDTO>();
		for (Incarico inc : lstIncarichi){
			IncaricoDTO incDTO = new IncaricoDTO(inc);
			lstIncarichiDTO.add(incDTO);
		}
		return lstIncarichiDTO;
	}
	
	// 1.2 trova gli incarichi di Dirigente non Generale per quel Dipartimento e quell'anno
	@Override
	public List<IncaricoDTO> findIncarichiByDipartimentoIDAndAnno(Integer dipartimentoID, int anno) {
		List<Incarico> lstIncarichi = incServizi.findIncarichiByDipartimentoIDAndAnno(dipartimentoID, anno);
		//System.out.println("OrganikoQryServiceImpl.findIncarichiByDipartimentoIDAndAnno() lstIncarichi("+dipartimentoID+","+anno+") empty? = "+lstIncarichi.isEmpty());
		List<IncaricoDTO> lstIncarichiDTO = new ArrayList<IncaricoDTO>();
		for (Incarico inc : lstIncarichi){
			IncaricoDTO incDTO = new IncaricoDTO(inc);
			lstIncarichiDTO.add(incDTO);
		}
		//PropertyComparator.sort(lstIncarichiDTO, new MutableSortDefinition("order",true,true));
		return lstIncarichiDTO;
	}
	
	// 1.3
	@Override
	public List<IncaricoDTO> findIncarichiByStrutturaIDAndAnno(Integer strutturaID, int anno) {
		List<Incarico> lstIncarichi = incServizi.findIncarichiByStrutturaIDAndAnno(strutturaID, anno);
		List<IncaricoDTO> lstIncarichiDTO = new ArrayList<IncaricoDTO>();
		for (Incarico inc : lstIncarichi){
			IncaricoDTO incDTO = new IncaricoDTO(inc);
			lstIncarichiDTO.add(incDTO);
		}
		return lstIncarichiDTO;
	}
	public List<IncaricoDTO> findIncarichiPopByStrutturaIDAndAnno(Integer strutturaID, int anno) {
		List<Incarico> lstIncarichi = incServizi.findIncarichiPopByStrutturaIDAndAnno(strutturaID, anno);
		List<IncaricoDTO> lstIncarichiDTO = new ArrayList<IncaricoDTO>();
		for (Incarico inc : lstIncarichi){
			IncaricoDTO incDTO = new IncaricoDTO(inc);
			lstIncarichiDTO.add(incDTO);
		}
		return lstIncarichiDTO;
	}
	// 2.1
	@Override
	public List<IncaricoDTO> findIncarichiByDirigenteIDAndAnno(Integer dirigenteID, int anno) {
		List<Incarico> lstIncarichi = incServizi.findIncarichiByDirigenteIDAndAnno(dirigenteID, anno);
		List<IncaricoDTO> lstIncarichiDTO = new ArrayList<IncaricoDTO>();
		for (Incarico inc : lstIncarichi){
			IncaricoDTO incDTO = new IncaricoDTO(inc);
			lstIncarichiDTO.add(incDTO);
		}
		return lstIncarichiDTO;
	}
	
	
	
	// Persona Giuridica
	
	
	
	// Persona Fisica
	
	

	@Override
	public PersonaFisicaDTO findPersonaFisicaById(Integer id) {
		PersonaFisicaDTO pf = new PersonaFisicaDTO(pfServizi.findById(id));
		return pf;
	}

	@Override
	public List<PersonaFisicaDTO> findDipendentiStrictByStrutturaIDAndAnno(Integer idStruttura, int anno) {
		List<PersonaFisica> lstPersone = pfServizi.findDipendentiStrictOfStructureIdAndAnno(idStruttura,anno);
		List<PersonaFisicaDTO> lstPersoneDTO = new ArrayList<PersonaFisicaDTO>();
		for (PersonaFisica pf : lstPersone){
			PersonaFisicaDTO pfDTO = new PersonaFisicaDTO(pf);
			lstPersoneDTO.add(pfDTO);
		}
		return lstPersoneDTO;
	}
	
	@Override
	public List<PersonaFisicaDTO> findDipendentiGlobalByStrutturaIDAndAnno(Integer idStruttura, int anno) {
		List<PersonaFisica> lstPersone = pfServizi.findDipendentiGlobalOfStructureIdAndAnno(idStruttura,anno);
		List<PersonaFisicaDTO> lstPersoneDTO = new ArrayList<PersonaFisicaDTO>();
		for (PersonaFisica pf : lstPersone){
			PersonaFisicaDTO pfDTO = new PersonaFisicaDTO(pf);
			lstPersoneDTO.add(pfDTO);
		}
		return lstPersoneDTO;
	}
	
	@Override
	public List<PersonaFisicaDTO> findDipendentiByDipartimentoIDAndAnno(Integer idDipartimento, int anno) {
		List<PersonaFisica> lstPersone = pfServizi.findDipendentiByDipartimentoIDAndAnno(idDipartimento,anno);
		List<PersonaFisicaDTO> lstPersoneDTO = new ArrayList<PersonaFisicaDTO>();
		for (PersonaFisica pf : lstPersone){
			PersonaFisicaDTO pfDTO = new PersonaFisicaDTO(pf);
			lstPersoneDTO.add(pfDTO);
		}
		return lstPersoneDTO;
	}
	
	@Override
	public List<PersonaFisicaDTO> findDipendentiByDipartimentoID(Integer idDipartimento) {
		List<PersonaFisica> lstPersone = pfServizi.findDipendentiByDipartimentoID(idDipartimento);
		List<PersonaFisicaDTO> lstPersoneDTO = new ArrayList<PersonaFisicaDTO>();
		for (PersonaFisica pf : lstPersone){
			PersonaFisicaDTO pfDTO = new PersonaFisicaDTO(pf);
			lstPersoneDTO.add(pfDTO);
		}
		return lstPersoneDTO;
	}
	
	// Persona Giuridica
	
	@Override
	public List<PersonaGiuridicaDTO> listSubStruttureByDipartimentoIDAndAnno(Integer padreID, int anno) {
		List<PersonaGiuridica> lstPersone = pgServizi.listSubStruttureByDipartimentoIDAndAnno(padreID, anno);
		List<PersonaGiuridicaDTO> lstPersoneDTO = new ArrayList<PersonaGiuridicaDTO>();
		for (PersonaGiuridica pg : lstPersone){
			PersonaGiuridicaDTO pgDTO = new PersonaGiuridicaDTO(pg);
			lstPersoneDTO.add(pgDTO);
		}
		return lstPersoneDTO;
	}
	
	//
	@Override
	public List<PersonaGiuridicaDTO> listAssessoratiAndAnno(int anno) {
		List<PersonaGiuridica> lstPersone = pgServizi.listAssessoratiAndAnno(anno);
		List<PersonaGiuridicaDTO> lstPersoneDTO = new ArrayList<PersonaGiuridicaDTO>();
		for (PersonaGiuridica pg : lstPersone){
			PersonaGiuridicaDTO pgDTO = new PersonaGiuridicaDTO(pg);
			lstPersoneDTO.add(pgDTO);
		}
		return lstPersoneDTO;
	}

	@Override
	public PersonaGiuridicaDTO findPersonaGiuridicaById(Integer id) {
		PersonaGiuridicaDTO pg = new PersonaGiuridicaDTO(pgServizi.findById(id));
		return pg;
	}

	@Override
	public PersonaGiuridicaDTO findDipartimentoByPersonaFisicaID(Integer pfID) {		
		PersonaFisicaDTO pf = this.findPersonaFisicaById(pfID);
		PersonaGiuridicaDTO pg = this.findPersonaGiuridicaById(pf.pgID);
		return this.findDipartimentoByPersonaGiuridicaID(pg.idPersona);
	}
	
	@Override
	public PersonaGiuridicaDTO findAssessoratoByPersonaFisicaID(Integer pfID) {
		PersonaFisicaDTO pf = this.findPersonaFisicaById(pfID);
		PersonaGiuridicaDTO pg = this.findPersonaGiuridicaById(pf.pgID);
		PersonaGiuridicaDTO dept = this.findDipartimentoByPersonaGiuridicaID(pg.idPersona);
		PersonaGiuridicaDTO ass = this.findPersonaGiuridicaById(dept.pgPadreID);
		return ass;
	}
	
	@Override
	public PersonaGiuridicaDTO findDipartimentoByPersonaGiuridicaID(Integer pgID) {
		PersonaGiuridicaDTO pg = this.findPersonaGiuridicaById(pgID);
		if (pg.dipartimento) return pg;
		else {
			int maxLevel = 5;
			while (maxLevel >0 ){
				int idPadre = pg.pgPadreID;
				PersonaGiuridicaDTO dept = this.findPersonaGiuridicaById(idPadre);
				if (dept.dipartimento) return dept;
				else {
					maxLevel--;
					pg=dept;
				}
			}
		}
		return pg;
	}
	
	
	@Override
	public List<PersonaGiuridicaDTO> findStruttureByStrutturaPadreIDAndAnno(Integer padreID, int anno) {
		PersonaGiuridica padre = pgServizi.findById(padreID);
		List<PersonaGiuridica> lstPersone = pgServizi.findStruttureByStrutturaPadreIDAndAnno(padre,anno);
		List<PersonaGiuridicaDTO> lstPersoneDTO = new ArrayList<PersonaGiuridicaDTO>();
		for (PersonaGiuridica pg : lstPersone){
			PersonaGiuridicaDTO pgDTO = new PersonaGiuridicaDTO(pg);
			lstPersoneDTO.add(pgDTO);
		}
		return lstPersoneDTO;
	}

	
	@Override
	public List<PersonaGiuridicaDTO> listDipartimentiByAssessoratoIDAndAnno(Integer assID, int anno) {
		List<PersonaGiuridica> lstPersone = pgServizi.listDipartimentiByAssessoratoIDAndAnno(assID,anno);
		List<PersonaGiuridicaDTO> lstPersoneDTO = new ArrayList<PersonaGiuridicaDTO>();
		for (PersonaGiuridica pg : lstPersone){
			PersonaGiuridicaDTO pgDTO = new PersonaGiuridicaDTO(pg);
			lstPersoneDTO.add(pgDTO);
		}
		return lstPersoneDTO;
	}
	
	

	

	

}
