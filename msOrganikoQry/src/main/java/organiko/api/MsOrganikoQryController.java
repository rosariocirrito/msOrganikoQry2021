package organiko.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import organiko.api.hs.OrganikoQryService;
import organiko.api.hs.pl.IncaricoDTO;
import organiko.api.hs.pl.PersonaFisicaDTO;
import organiko.api.hs.pl.PersonaGiuridicaDTO;


@RestController
@RequestMapping(value="/api")
public class MsOrganikoQryController {
	
	
	@Autowired
    private OrganikoQryService orgServizi;
	
	 //
    @RequestMapping(value="/findIncaricoById/{id}")
    public IncaricoDTO findIncaricoById(@PathVariable(value = "id") int id) throws IOException {
    	IncaricoDTO inc = orgServizi.findIncaricoById(id);
    	if (inc==null) throw new FileNotFoundException("/findIncaricoById/"+id);
    	else return inc;
    }
    
    @RequestMapping(value="/findIncarichiApicaliByAssessoratoIDAndAnno/{assessoratoID}/{anno}")
    public List<IncaricoDTO> findIncarichiApicaliByAssessoratoIDAndAnno(@PathVariable(value = "assessoratoID") int assessoratoID,
    		@PathVariable(value = "anno") int anno) throws IOException {
    	List<IncaricoDTO> lista = orgServizi.findIncarichiApicaliByAssessoratoIDAndAnno(assessoratoID, anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/findIncarichiApicaliByAssessoratoIDAndAnno/"+assessoratoID+"/"+anno);
    	else return lista;
    }
   
    @RequestMapping(value="/findIncarichiApicaliByDipartimentoIDAndAnno/{dipartimentoID}/{anno}")
    public List<IncaricoDTO> findIncarichiApicaliByDipartimentoIDAndAnno(@PathVariable(value = "dipartimentoID") int dipartimentoID,
    		@PathVariable(value = "anno") int anno) throws IOException {
    	List<IncaricoDTO> lista = orgServizi.findIncarichiApicaliByDipartimentoIDAndAnno(dipartimentoID, anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/findIncarichiApicaliByDipartimentoIDAndAnno/"+dipartimentoID+"/"+anno);
    	else return lista;
    }
    
    @RequestMapping(value="/findIncarichiByDipartimentoIDAndAnno/{dipartimentoID}/{anno}")
    public List<IncaricoDTO> findIncarichiByDipartimentoIDAndAnno(@PathVariable(value = "dipartimentoID") int dipartimentoID,
    		@PathVariable(value = "anno") int anno) throws IOException {
    	List<IncaricoDTO> lista = orgServizi.findIncarichiByDipartimentoIDAndAnno(dipartimentoID, anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/findIncarichiApicaliByDipartimentoIDAndAnno/"+dipartimentoID+"/"+anno);
    	else return lista;
    }
    
    @RequestMapping(value="/findIncarichiByStrutturaIDAndAnno/{strutturaID}/{anno}")
    public List<IncaricoDTO> findIncarichiByStrutturaIDAndAnno(@PathVariable(value = "strutturaID") int strutturaID,
    		@PathVariable(value = "anno") int anno) throws IOException {
    	List<IncaricoDTO> lista = orgServizi.findIncarichiByStrutturaIDAndAnno(strutturaID, anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/findIncarichiByStrutturaIDAndAnno/"+strutturaID+"/"+anno);
    	else return lista;
    }
    
    @RequestMapping(value="/findIncarichiPopByStrutturaIDAndAnno/{strutturaID}/{anno}")
    public List<IncaricoDTO> findIncarichiPopByStrutturaIDAndAnno(@PathVariable(value = "strutturaID") int strutturaID,
    		@PathVariable(value = "anno") int anno) throws IOException {
    	List<IncaricoDTO> lista = orgServizi.findIncarichiPopByStrutturaIDAndAnno(strutturaID, anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/findIncarichiPopByStrutturaIDAndAnno/"+strutturaID+"/"+anno);
    	else return lista;
    }
    
    @RequestMapping(value="/findIncarichiByDirigenteIDAndAnno/{dirigenteID}/{anno}")
    public List<IncaricoDTO> findIncarichiByDirigenteIDAndAnno(@PathVariable(value = "dirigenteID") int dirigenteID,
    		@PathVariable(value = "anno") int anno) throws IOException {
    	List<IncaricoDTO> lista = orgServizi.findIncarichiByDirigenteIDAndAnno(dirigenteID, anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/findIncarichiByDirigenteIDAndAnno/"+dirigenteID+"/"+anno);
    	else return lista;
    }
    
    // ----------------------------------------------
    @RequestMapping(value="/findDipendentiStrictByStrutturaIDAndAnno/{idStruttura}/{anno}")
    public List<PersonaFisicaDTO> findDipendentiStrictByStrutturaIDAndAnno(@PathVariable(value = "idStruttura") int idStruttura,
    		@PathVariable(value = "anno") int anno) throws IOException {
    	List<PersonaFisicaDTO> lista = orgServizi.findDipendentiStrictByStrutturaIDAndAnno(idStruttura, anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/findIncarichiByStrutturaIDAndAnno/"+idStruttura+"/"+anno);
    	else return lista;
    }
    
    @RequestMapping(value="/findDipendentiGlobalByStrutturaIDAndAnno/{idStruttura}/{anno}")
    public List<PersonaFisicaDTO> findDipendentiGlobalByStrutturaIDAndAnno(@PathVariable(value = "idStruttura") int idStruttura,
    		@PathVariable(value = "anno") int anno) throws IOException {
    	List<PersonaFisicaDTO> lista = orgServizi.findDipendentiGlobalByStrutturaIDAndAnno(idStruttura, anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/findDipendentiGlobalByStrutturaIDAndAnno/"+idStruttura+"/"+anno);
    	else return lista;
    }
    
    @RequestMapping(value="/findDipendentiByDipartimentoIDAndAnno/{idDipartimento}/{anno}")
    public List<PersonaFisicaDTO> findDipendentiByDipartimentoIDAndAnno(@PathVariable(value = "idDipartimento") int idDipartimento,
    		@PathVariable(value = "anno") int anno) throws IOException {
    	List<PersonaFisicaDTO> lista = orgServizi.findDipendentiByDipartimentoIDAndAnno(idDipartimento, anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/findDipendentiGlobalByDipartimentoIDAndAnno/"+idDipartimento+"/"+anno);
    	else return lista;
    }
    
    
    @RequestMapping(value="/findPersonaFisicaById/{id}")
    public PersonaFisicaDTO findPersonaFisicaById(@PathVariable(value = "id") int id) throws IOException {
    	PersonaFisicaDTO pf = orgServizi.findPersonaFisicaById(id);
    	if (pf==null) throw new FileNotFoundException("/findPersonaFisicaById/"+id);
    	else return pf;
    }
    
 // ----------------------------------------------
 // ----------------------------------------------
    @RequestMapping(value="/listSubStruttureByDipartimentoIDAndAnno/{padreID}/{anno}")
    public List<PersonaGiuridicaDTO> listSubStruttureByDipartimentoIDAndAnno(@PathVariable(value = "padreID") int padreID,
    		@PathVariable(value = "anno") int anno) throws IOException {
    	List<PersonaGiuridicaDTO> lista = orgServizi.listSubStruttureByDipartimentoIDAndAnno(padreID, anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/listSubStruttureByDipartimentoIDAndAnno/"+padreID+"/"+anno);
    	else return lista;
    }
    
    @RequestMapping(value="/listAssessoratiAndAnno/{anno}")
    public List<PersonaGiuridicaDTO> listAssessoratiAndAnno(@PathVariable(value = "anno") int anno) throws IOException {
    	List<PersonaGiuridicaDTO> lista = orgServizi.listAssessoratiAndAnno(anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/listAssessoratiAndAnno/"+anno);
    	else return lista;
    }
    
    @RequestMapping(value="/listDipartimentiByAssessoratoIDAndAnno/{assID}/{anno}")
    public List<PersonaGiuridicaDTO> listDipartimentiByAssessoratoIDAndAnno(@PathVariable(value = "assID") int assID, @PathVariable(value = "anno") int anno) throws IOException {
    	List<PersonaGiuridicaDTO> lista = orgServizi.listDipartimentiByAssessoratoIDAndAnno(assID, anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/listDipartimentiAndAnno/"+anno);
    	else return lista;
    }
    
    @RequestMapping(value="/findStruttureByStrutturaPadreIDAndAnno/{padreID}/{anno}")
    public List<PersonaGiuridicaDTO> findStruttureByStrutturaPadreID(@PathVariable(value = "padreID") int padreID, @PathVariable(value = "anno") int anno) throws IOException {
    	List<PersonaGiuridicaDTO> lista = orgServizi.findStruttureByStrutturaPadreIDAndAnno(padreID,anno);
    	if (lista.isEmpty()) throw new FileNotFoundException("/findStruttureByStrutturaPadreID/"+padreID);
    	else return lista;
    }
    
    @RequestMapping(value="/findPersonaGiuridicaById/{id}")
    public PersonaGiuridicaDTO findPersonaGiuridicaById(@PathVariable(value = "id") int id) throws IOException {
    	PersonaGiuridicaDTO pg = orgServizi.findPersonaGiuridicaById(id);
    	if (pg==null) throw new FileNotFoundException("/findPersonaGiuridicaById/"+id);
    	else return pg;
    }
    
    @RequestMapping(value="/findDipartimentoByPersonaFisicaId/{pfID}")
    public PersonaGiuridicaDTO findDipartimentoByPersonaFisicaID(@PathVariable(value = "pfID") int pfID) throws IOException {
    	PersonaGiuridicaDTO pg = orgServizi.findDipartimentoByPersonaFisicaID(pfID);
    	if (pg==null) throw new FileNotFoundException("/findDipartimentoByPersonaFisicaID/"+pfID);
    	else return pg;
    }
    
    @RequestMapping(value="/findDipartimentoByPersonaGiuridicaID/{pgID}")
    public PersonaGiuridicaDTO findDipartimentoByPersonaGiuridicaID(@PathVariable(value = "pgID") int pgID) throws IOException {
    	PersonaGiuridicaDTO pg = orgServizi.findDipartimentoByPersonaGiuridicaID(pgID);
    	if (pg==null) throw new FileNotFoundException("/findDipartimentoByPersonaGiuridicaID/"+pgID);
    	else return pg;
    }

    @RequestMapping(value="/findAssessoratoByPersonaFisicaId/{pfID}")
    public PersonaGiuridicaDTO findAssessoratoByPersonaFisicaId(@PathVariable(value = "pfID") int pfID) throws IOException {
    	PersonaGiuridicaDTO pg = orgServizi.findAssessoratoByPersonaFisicaID(pfID);
    	if (pg==null) throw new FileNotFoundException("/findAssessoratoByPersonaFisicaId/"+pfID);
    	else return pg;
    }
    
    
}
