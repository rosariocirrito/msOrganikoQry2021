package organiko.domain.incarico;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import organiko.domain.personaFisica.PersonaFisica;
import organiko.domain.personaFisica.PersonaFisicaService;
import organiko.domain.personaGiuridica.PersonaGiuridica;
import organiko.domain.personaGiuridica.PersonaGiuridicaService;

@Service("incaricoQryService")
public class IncaricoQryServiceImpl implements IncaricoQryService{

	private Log log = LogFactory.getLog(IncaricoQryServiceImpl.class);

	// vedi Spring Data Pro Spring 3 p380 al posto di em uso il Repository
	@Autowired 
	private IncaricoRepository repo;
	
	@Autowired 
	private PersonaGiuridicaService pgServizi;
	@Autowired 
	private PersonaFisicaService pfServizi;
	
	// richiamato da findById
	public Incarico completaIncarico(Incarico inc){
		//
		PersonaGiuridica struttura = inc.getPersonaGiuridica();
		inc.setDenominazioneStruttura(struttura.getDenominazione());
		inc.setCompetenzeStruttura(struttura.getCompetenze());
		//
		PersonaGiuridica dept = pgServizi.findDipartimentoByPersonaGiuridicaID(struttura.getIdPersona());
		inc.setIdDept(dept.getIdPersona());
		if (struttura.isDipartimento()) inc.setIncaricoDipartimentale(true); 
		inc.setDenominazioneDipartimento(dept.getDenominazione());
		//
		//String responsabile = pfServizi.findById(inc.getIdPF()).getStringa();
		PersonaFisica manager = inc.getPersonaFisica();
		String responsabile ="";
		if (manager != null) {
			responsabile = manager.getCognomeNome();
		}
		else {
		  responsabile = "responsabile sconosciuto";
		}
		String carica = struttura.getCarica();
		if (carica == null ) carica = "Dirigente";
		String responsabileCarica = carica + " - " + responsabile; 
		inc.setResponsabile(responsabileCarica);
		inc.setCarica(carica);
		return inc;
	}
	
	@Override
	public Incarico findById(Integer id) {
		Incarico inc = repo.getOne(id);
		this.completaIncarico(inc);
		return inc;
	}

	
	@Override
	@Transactional(readOnly=true)
	public List<Incarico> findIncarichiApicaliByAssessoratoIDAndAnno(Integer assessoratoID, int anno) {
		List<PersonaGiuridica> listDipartimenti = pgServizi.listDipartimentiByAssessoratoIDAndAnno(assessoratoID, anno);
		List<Incarico> lstIncarichiAnno = new ArrayList<Incarico>();
		if (null!=listDipartimenti && !listDipartimenti.isEmpty()){
		for(PersonaGiuridica dept: listDipartimenti){
			List<Incarico> incDipartimento = repo.findAllByPersonaGiuridica(dept);
			if (null!=incDipartimento && !incDipartimento.isEmpty()){
				for (Incarico inc : incDipartimento){
					if(anno>=inc.getAnnoinz() && anno<=inc.getAnnofin()){
						this.completaIncarico(inc);
						if(!lstIncarichiAnno.contains(inc)) lstIncarichiAnno.add(inc);
					}
				}
			}
		}
		}
		//System.out.println("IncaricoQryServiceImpl.findIncarichiApicaliByDipartimentoIDAndAnno(("+dipartimentoID+","+anno+") lstIncarichiAnno? = "+lstIncarichiAnno.isEmpty());
		PropertyComparator.sort(lstIncarichiAnno, new MutableSortDefinition("responsabile",true,true));
		return lstIncarichiAnno;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Incarico> findIncarichiApicaliByDipartimentoIDAndAnno(Integer dipartimentoID, int anno) {
		PersonaGiuridica dept = pgServizi.findById(dipartimentoID);
		List<Incarico> incDipartimento = repo.findAllByPersonaGiuridica(dept);
		List<Incarico> lstIncarichiAnno = new ArrayList<Incarico>();
		for (Incarico inc : incDipartimento){
			if(anno>=inc.getAnnoinz() && anno<=inc.getAnnofin()){
				this.completaIncarico(inc);
				if(!lstIncarichiAnno.contains(inc)) lstIncarichiAnno.add(inc);
			}
		}
		//System.out.println("IncaricoQryServiceImpl.findIncarichiApicaliByDipartimentoIDAndAnno(("+dipartimentoID+","+anno+") lstIncarichiAnno? = "+lstIncarichiAnno.isEmpty());
		PropertyComparator.sort(lstIncarichiAnno, new MutableSortDefinition("responsabile",true,true));
		return lstIncarichiAnno;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Incarico> findIncarichiByDipartimentoIDAndAnno(Integer dipartimentoID, int anno) {
		List<Incarico> lstIncarichiAnno = new ArrayList<Incarico>();
		List<PersonaGiuridica> struDept = pgServizi.listSubStruttureByDipartimentoIDAndAnno(dipartimentoID, anno);
		//System.out.println("IncaricoQryServiceImpl.findIncarichiByDipartimentoIDAndAnno() lstIncarichi("+dipartimentoID+","+anno+") struDeptEmpty? = "+struDept.isEmpty());
		
		if(!struDept.isEmpty()){
			for (PersonaGiuridica struttura : struDept){
				List<Incarico> listIncarichiStruttura = this.findIncarichiByStrutturaIDAndAnno(struttura.getIdPersona(), anno);
				for (Incarico inc : listIncarichiStruttura){
					if(!lstIncarichiAnno.contains(inc)) lstIncarichiAnno.add(inc);
				}
			}
		}
		PropertyComparator.sort(lstIncarichiAnno, new MutableSortDefinition("responsabile",true,true));
		return lstIncarichiAnno;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Incarico> findIncarichiByStrutturaIDAndAnno(Integer strutturaID, int anno) {
		PersonaGiuridica struttura = pgServizi.findById(strutturaID);
		List<Incarico> incStruttura = repo.findAllByPersonaGiuridica(struttura);
		List<Incarico> lstIncarichiAnno = new ArrayList<Incarico>();
		for (Incarico inc : incStruttura){
			if(anno>=inc.getAnnoinz() && anno<=inc.getAnnofin()){
				this.completaIncarico(inc);
				/*
				OpPersonaFisica dirig = pfServizi.findById(inc.getIdPF());
				inc.setOpPersonaFisica(dirig);
				inc.setOpPersonaGiuridica(struttura);
				*/
				lstIncarichiAnno.add(inc);
			}
		}
		PropertyComparator.sort(lstIncarichiAnno, new MutableSortDefinition("stringa",true,true));
		return lstIncarichiAnno;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Incarico> findIncarichiPopByStrutturaIDAndAnno(Integer strutturaID, int anno) {
		List<Incarico> lstIncarichiAnno = new ArrayList<Incarico>();
		PersonaGiuridica struttura = pgServizi.findById(strutturaID);
		//
		List<PersonaGiuridica> lststrutturePOP = struttura.getOpPersonaGiuridicas();
		if(!lststrutturePOP.isEmpty()) {
			for(PersonaGiuridica stru : lststrutturePOP) {			
				List<Incarico> incStruttura = repo.findAllByPersonaGiuridica(stru);		
				for (Incarico inc : incStruttura){
					if(anno>=inc.getAnnoinz() && anno<=inc.getAnnofin()){
						this.completaIncarico(inc);
						/*
						OpPersonaFisica dirig = pfServizi.findById(inc.getIdPF());
						inc.setOpPersonaFisica(dirig);
						inc.setOpPersonaGiuridica(struttura);
						*/
						lstIncarichiAnno.add(inc);
					}
				}
				PropertyComparator.sort(lstIncarichiAnno, new MutableSortDefinition("stringa",true,true));
				}
			}
		return lstIncarichiAnno;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Incarico> findIncarichiByDirigenteIDAndAnno(Integer dirigenteID, int anno) {
		PersonaFisica dirigente = pfServizi.findById(dirigenteID);
		List<Incarico> incDirigente = repo.findAllByPersonaFisica(dirigente);
		List<Incarico> lstIncarichiAnno = new ArrayList<Incarico>();
		for (Incarico inc : incDirigente){
			if(anno>=inc.getAnnoinz() && anno<=inc.getAnnofin()){ //&& !inc.getOpPersonaGiuridica().isDipartimento()){
				this.completaIncarico(inc);				
				lstIncarichiAnno.add(inc);
			}
		}
		PropertyComparator.sort(lstIncarichiAnno, new MutableSortDefinition("stringa",true,true));
		return lstIncarichiAnno;
	}

}
