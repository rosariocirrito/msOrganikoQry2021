package organiko.domain.personaFisica;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import organiko.domain.personaAfferenza.PersonaAfferenza;
import organiko.domain.personaFisicaTipo.PersonaFisicaTipoService;
import organiko.domain.personaGiuridica.PersonaGiuridica;
import organiko.domain.personaGiuridica.PersonaGiuridicaService;



@Service("personaFisicaService")
@Repository
@Transactional
public class PersonaFisicaServiceImpl implements PersonaFisicaService {
	
	private Log log = LogFactory.getLog(PersonaFisicaServiceImpl.class);

	// vedi Spring Data Pro Spring 3 p380 al posto di em uso il Repository
	@Autowired 
	private PersonaFisicaRepository repo;
	@Autowired 
	private PersonaGiuridicaService pgServizi;
	@Autowired 
	private PersonaFisicaTipoService tipoServizi;
	


	@Transactional(readOnly=true)
	@Override
	public PersonaFisica findById(Integer id) {
		return repo.getOne(id);
	}

	
	//--------------------------------------------------------------------------------

	@Override
	public List<PersonaFisica> findDipendentiStrictOfStructureIdAndAnno(Integer strutturaId, int anno) {
		//log.info("OpPersonaFisicaServiceImpl findDipendentiStrictOfStructureIdAndAnno anno: "+ anno);
		PersonaGiuridica struttura = pgServizi.findById(strutturaId);
		List<PersonaFisica> dipendentiInternal = new ArrayList(struttura.getDipendentiInternal());
        List<PersonaFisica> sortedDipendenti = new ArrayList();
        for(PersonaFisica dip : dipendentiInternal) {
        	//log.info("dip.: "+dip.getStringa()+ " id: "+dip.getIdpersona() +" annoiniz.: "+dip.getAnnoIniz() );
        	if (anno >= dip.getAnnoIniz()) {
        		//log.info("aggiunto: "+dip.getCognomeNome() );
        		sortedDipendenti.add(dip);
        	}
        	else {
        		//log.info("scartato: "+dip.getCognomeNome() );
        	}
        }
        List<PersonaAfferenza> afferenze = new ArrayList(struttura.getOpPersonaAfferenzas());     
        for(PersonaAfferenza aff: afferenze){
        	PersonaFisica dip = aff.getOpPersonaFisica();
        	if (!sortedDipendenti.contains(dip)){
        		if (anno >= aff.getAnnoinz() && anno <= aff.getAnnofin()) {
        			//log.info("ripescato da afferenze struttura: "+dip.getCognomeNome() );
        			sortedDipendenti.add(dip);
        		}
        	}	
        }
       
        PropertyComparator.sort(sortedDipendenti, new MutableSortDefinition("order",true,true));
        return sortedDipendenti;
	}
	

	@Override
	public List<PersonaFisica> findDipendentiGlobalOfStructureIdAndAnno(Integer strutturaId, int anno) {
		//System.out.println("struttura principale:" + struttura.getDenominazione() );
		// dipendenti diretti
		PersonaGiuridica struttura = pgServizi.findById(strutturaId);
		List<PersonaFisica> sortedDipendenti = new ArrayList(this.findDipendentiStrictOfStructureIdAndAnno(struttura.getIdPersona(), anno));
        //
        for(PersonaGiuridica stru : struttura.getOpPersonaGiuridicas()){
        	// dipendenti diretti
        	List<PersonaFisica> struDipendenti = this.findDipendentiStrictOfStructureIdAndAnno(stru.getIdPersona(), anno);
            if(!struDipendenti.isEmpty()) 
            	for(PersonaFisica dip2 : struDipendenti) {
	            	if (!sortedDipendenti.contains(dip2)){
	            		sortedDipendenti.add(dip2);
	            	}
            	}
            	
            // per ogni struttura vedo se c'� una sottostruttura e ripeto il procedimento
            List<PersonaGiuridica> lstSubStr = stru.getOpPersonaGiuridicas();
            if(lstSubStr != null && !lstSubStr.isEmpty()){
                for(PersonaGiuridica subStr : lstSubStr){
                    List<PersonaFisica> lstDipSub = this.findDipendentiStrictOfStructureIdAndAnno(subStr.getIdPersona(), anno);
                    if(lstDipSub != null && !lstDipSub.isEmpty())
                    	for(PersonaFisica dip3 : lstDipSub) {
        	            	if (!sortedDipendenti.contains(dip3)){
        	            		sortedDipendenti.add(dip3);
        	            	}
                    	}
                }
            }
        }
        PropertyComparator.sort(sortedDipendenti, new MutableSortDefinition("order",true,true));
        return sortedDipendenti;
	}
	
	@Override
	public List<PersonaFisica> findDipendentiByDipartimentoIDAndAnno(Integer idDipartimento, int anno) {
		//System.out.println("struttura principale:" + struttura.getDenominazione() );
		// dipendenti diretti
		
		PersonaGiuridica dipartimento = pgServizi.findById(idDipartimento);
		List<PersonaFisica> sortedDipendentiDept = new ArrayList(this.findDipendentiStrictOfStructureIdAndAnno(idDipartimento, anno));

        for(PersonaGiuridica struIntermedia : dipartimento.getOpPersonaGiuridicas()){
        	// dipendenti diretti
        	List<PersonaFisica> struDipendenti = this.findDipendentiStrictOfStructureIdAndAnno(struIntermedia.getIdPersona(), anno);
            if(!struDipendenti.isEmpty()) 
            	for(PersonaFisica dip2 : struDipendenti) {
	            	if (!sortedDipendentiDept.contains(dip2)){
	            		sortedDipendentiDept.add(dip2);
	            	}
            	}
            	
            // per ogni struttura vedo se c'� una sottostruttura e ripeto il procedimento
            List<PersonaGiuridica> lstStrUOB = struIntermedia.getOpPersonaGiuridicas();
            if(lstStrUOB != null && !lstStrUOB.isEmpty()){
                for(PersonaGiuridica strUOB : lstStrUOB){
                    List<PersonaFisica> lstDipSub = this.findDipendentiStrictOfStructureIdAndAnno(strUOB.getIdPersona(), anno);
                    if(lstDipSub != null && !lstDipSub.isEmpty())
                    	for(PersonaFisica dip3 : lstDipSub) {
        	            	if (!sortedDipendentiDept.contains(dip3)){
        	            		sortedDipendentiDept.add(dip3);
        	            	}
                    	}
                }
            }
        }
        
        PropertyComparator.sort(sortedDipendentiDept, new MutableSortDefinition("order",true,true));
        return sortedDipendentiDept;
	}

	
	//
	@Override
	public List<PersonaFisica> findDipendentiByDipartimentoID(Integer idDipartimento) {
		//System.out.println("struttura principale:" + struttura.getDenominazione() );
		// dipendenti diretti
		
		PersonaGiuridica dipartimento = pgServizi.findById(idDipartimento);
		List<PersonaFisica> sortedDipendentiDept = new ArrayList(this.findDipendentiStrictOfStructureId(idDipartimento));

        for(PersonaGiuridica struIntermedia : dipartimento.getOpPersonaGiuridicas()){
        	// dipendenti diretti
        	List<PersonaFisica> struDipendenti = this.findDipendentiStrictOfStructureId(struIntermedia.getIdPersona());
            if(!struDipendenti.isEmpty()) 
            	for(PersonaFisica dip2 : struDipendenti) {
	            	if (!sortedDipendentiDept.contains(dip2)){
	            		sortedDipendentiDept.add(dip2);
	            	}
            	}
            	
            // per ogni struttura vedo se c'� una sottostruttura e ripeto il procedimento
            List<PersonaGiuridica> lstStrUOB = struIntermedia.getOpPersonaGiuridicas();
            if(lstStrUOB != null && !lstStrUOB.isEmpty()){
                for(PersonaGiuridica strUOB : lstStrUOB){
                    List<PersonaFisica> lstDipSub = this.findDipendentiStrictOfStructureId(strUOB.getIdPersona());
                    if(lstDipSub != null && !lstDipSub.isEmpty())
                    	for(PersonaFisica dip3 : lstDipSub) {
        	            	if (!sortedDipendentiDept.contains(dip3)){
        	            		sortedDipendentiDept.add(dip3);
        	            	}
                    	}
                }
            }
        }
        
        PropertyComparator.sort(sortedDipendentiDept, new MutableSortDefinition("order",true,true));
        return sortedDipendentiDept;
	}

	
	// metodi privati
	private List<PersonaFisica> findDipendentiStrictOfStructureId(Integer strutturaId) {
		//log.info("OpPersonaFisicaServiceImpl findDipendentiStrictOfStructureIdAndAnno anno: "+ anno);
		PersonaGiuridica struttura = pgServizi.findById(strutturaId);
        List<PersonaFisica> sortedDipendenti = new ArrayList(struttura.getDipendentiInternal());
        List<PersonaAfferenza> afferenze = new ArrayList(struttura.getOpPersonaAfferenzas());     
        for(PersonaAfferenza aff: afferenze){
        	PersonaFisica dip = aff.getOpPersonaFisica();
        	if (!sortedDipendenti.contains(dip)){
        		 sortedDipendenti.add(dip);		
        	}	
        }
        PropertyComparator.sort(sortedDipendenti, new MutableSortDefinition("order",true,true));
        return sortedDipendenti;
	}
} // -------------------------------------
