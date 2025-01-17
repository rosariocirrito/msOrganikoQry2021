package organiko.domain.personaGiuridica;

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

import organiko.domain.personaGiuridica.PersonaGiuridica.OpPersonaGiuridicaStatoEnum;
import organiko.domain.personaGiuridicaTipo.PersonaGiuridicaTipoService;



@Service("personaGiuridicaService")
@Repository
@Transactional
public class PersonaGiuridicaServiceImpl implements PersonaGiuridicaService {
	
	private Log log = LogFactory.getLog(PersonaGiuridicaServiceImpl.class);

	// vedi Spring Data Pro Spring 3 p380 al posto di em uso il Repository
	@Autowired 
	private PersonaGiuridicaRepository repo;
	@Autowired 
	private PersonaGiuridicaTipoService serviziTipo;
	
	
	@Transactional(readOnly=true)
	@Override
	public PersonaGiuridica findById(Integer id) {
		return repo.getOne(id);
	}
	
	
	
	@Transactional(readOnly=true)
	@Override
	public List<PersonaGiuridica> listSubStruttureByDipartimentoIDAndAnno(Integer padreID, int anno) {
		PersonaGiuridica dipartimento = this.findById(padreID);
		List<PersonaGiuridica> lista = new ArrayList<PersonaGiuridica>();
		List<PersonaGiuridica> listaLev1 = repo.findByOpPersonaGiuridica(dipartimento);
		if (!listaLev1.isEmpty()) {
			for (PersonaGiuridica struLev1 : listaLev1){
				// se � attiva aggiungila
				if (struLev1.getStato().equals(OpPersonaGiuridicaStatoEnum.ATTIVO)) {
					lista.add(struLev1);
				}
				// se � cancellata aggiungila in base all'anno di cancellazione
				if (struLev1.getStato().equals(OpPersonaGiuridicaStatoEnum.CANCELLATO)) {
					int annoCancellazione = struLev1.getDataCambioStato().getYear() +1900;
					if (annoCancellazione >= anno)
					lista.add(struLev1);
				}
				// itero su eventuali UOB
				List<PersonaGiuridica> listaLev2 = repo.findByOpPersonaGiuridica(struLev1);
				if (!listaLev2.isEmpty()) {
					for (PersonaGiuridica struLev2 : listaLev2){
						// se � attiva aggiungila
						if (struLev2.getStato().equals(OpPersonaGiuridicaStatoEnum.ATTIVO)) {
							lista.add(struLev2);
						}
						// se � cancellata aggiungila in base all'anno di cancellazione
						if (struLev2.getStato().equals(OpPersonaGiuridicaStatoEnum.CANCELLATO)) {
							int annoCancellazione = struLev2.getDataCambioStato().getYear() +1900;
							if (annoCancellazione >= anno)
							lista.add(struLev2);
						}
					}
				}
			}
		}
        PropertyComparator.sort(lista, new MutableSortDefinition("codice",true,true));
		return lista;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<PersonaGiuridica> listDipartimentiByAssessoratoIDAndAnno(Integer assID, int anno) {
		PersonaGiuridica assessorato = repo.getOne(assID);
		List<PersonaGiuridica> listStruttureAssto = repo.findByOpPersonaGiuridica(assessorato);
		List<PersonaGiuridica> listaAnno = new ArrayList<PersonaGiuridica>();
		//
		for (PersonaGiuridica stru : listStruttureAssto){
			if(!stru.isCancellata()){
				listaAnno.add(stru);
			}
			else {
			int annoCancellazione = stru.getDataCambioStato().getYear() +1900;
			if (annoCancellazione >= anno) listaAnno.add(stru);
			}
		}
		PropertyComparator.sort(listaAnno, new MutableSortDefinition("codice",true,true));
		return listaAnno;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<PersonaGiuridica> listAssessoratiAndAnno(int anno) {
		List<PersonaGiuridica> lista = new ArrayList<PersonaGiuridica>();
		List<PersonaGiuridica> listaAnno = new ArrayList<PersonaGiuridica>();
		List<PersonaGiuridica> listaAss = repo.findByOpPersonaGiuridicaTipo(serviziTipo.findById(7));
		lista.addAll(listaAss);
		//
		for (PersonaGiuridica stru : lista){
			if(!stru.isCancellata()){
				listaAnno.add(stru);
			}
			else {
			int annoCancellazione = stru.getDataCambioStato().getYear() +1900;
			if (annoCancellazione >= anno) listaAnno.add(stru);
			}
		}
		PropertyComparator.sort(listaAnno, new MutableSortDefinition("codice",true,true));
		return listaAnno;
	} 
	
	


	@Transactional(readOnly=true)
	@Override
	public List<PersonaGiuridica> findStruttureByStrutturaPadreIDAndAnno(PersonaGiuridica padre, int anno) {
		List<PersonaGiuridica> lista = repo.findByOpPersonaGiuridica(padre);
		List<PersonaGiuridica> listaAnno = new ArrayList<PersonaGiuridica>();
		for (PersonaGiuridica stru : lista){
			if(!stru.isCancellata()){
				listaAnno.add(stru);
			}
			else {
			int annoCancellazione = stru.getDataCambioStato().getYear() +1900;
			if (annoCancellazione >= anno) listaAnno.add(stru);
			}
		}
		PropertyComparator.sort(listaAnno, new MutableSortDefinition("codice",true,true));
		return listaAnno;
	}

	

	@Transactional(readOnly=true)
	@Override
	public PersonaGiuridica findDipartimentoByPersonaGiuridicaID(Integer pgID) {
		//log.info("pgID:"+pgID);
		PersonaGiuridica pg = this.findById(pgID);
		if (pg.isDipartimento()) return pg;
		else {
			int maxLevel = 5;
			//log.info("maxLevel:"+maxLevel);
			while (maxLevel >0 ){
				int idPadre = pg.getIdPadre();
				//log.info("idPadre:"+idPadre);
				PersonaGiuridica dept = this.findById(idPadre);
				if (dept.isDipartimento()) return dept;
				else {
					maxLevel--;
					pg=dept;
				}
			}
		}
		return pg;
	}

	
}// --------------------
