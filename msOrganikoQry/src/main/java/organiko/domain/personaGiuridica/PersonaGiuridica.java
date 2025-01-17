package organiko.domain.personaGiuridica;

import java.util.*;

import javax.persistence.*;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import organiko.domain.incarico.Incarico;
import organiko.domain.persona.Persona;
import organiko.domain.personaAfferenza.PersonaAfferenza;
import organiko.domain.personaFisica.PersonaFisica;
import organiko.domain.personaGiuridicaTipo.PersonaGiuridicaTipo;

/**
 * The persistent class for the op_persona_giuridica database table.
 * 
 */
@Entity
@Table(name = "op_persona_giuridica")

public class PersonaGiuridica extends Persona {
	
    private static final long serialVersionUID = 1L;
	
    public enum OpPersonaGiuridicaStatoEnum{
		CANCELLATO,
		ATTIVO,
		NASCOSTO
    }

    private String denominazione;

    private String codice;
    
    private String competenze;
    
    private String carica;

    @Column(name = "percorso_denominazione")
    private String percorsoDenominazione;
	
    @Enumerated
    private OpPersonaGiuridicaStatoEnum stato;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_cambio_stato")
    private Date dataCambioStato;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inserimento")
    private Date dataInserimento;

    // bi-directional many-to-one association to OpPersonaGiuridicaTipo
    // @ManyToOne(fetch = FetchType.EAGER) 2015/07/20
    @ManyToOne()
    @JoinColumn(name = "op_persona_giuridica_tipo_idop_persona_giuridica_tipo")
    private PersonaGiuridicaTipo opPersonaGiuridicaTipo;

    // bi-directional many-to-one association to OpPersonaAfferenza
    // @OneToMany(mappedBy = "opPersonaGiuridica", fetch = FetchType.LAZY) 2015/07/20
    @OneToMany(mappedBy = "opPersonaGiuridica")
    private Set<PersonaAfferenza> opPersonaAfferenzas = new HashSet<PersonaAfferenza>();
    
    
    //@OneToMany(mappedBy = "opPersonaGiuridica",fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "personaGiuridica")
    private Set<Incarico> incarichi = new HashSet<Incarico>();

    
    // bi-directional many-to-one association to OpPersonaGiuridica
    //@ManyToOne(fetch = FetchType.EAGER) // non mettere lazy
    @ManyToOne() // non mettere lazy
    @JoinColumn(name = "padre")
    private PersonaGiuridica opPersonaGiuridica;

    // bi-directional one-to-many association
    @OneToMany(mappedBy = "opPersonaGiuridica")
    private Set<PersonaFisica> dipendenti = new HashSet<PersonaFisica>();

    // bi-directional many-to-one association to OpPersonaGiuridica
    //@OneToMany(mappedBy = "opPersonaGiuridica", fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "opPersonaGiuridica",fetch = FetchType.EAGER)
    private Set<PersonaGiuridica> opPersonaGiuridicas = new HashSet<PersonaGiuridica>();

    // bi-directional one-to-many association 
    // many Obiettivo to one Obiettivo 
    // owner side lato inverso
    //@OneToMany(mappedBy = "struttura") // non mettere lazy
    //@OrderBy("tipo")
    //private List<Obiettivo> obiettivi = new ArrayList<Obiettivo>();

    // ______________ transient 
    
    transient private int anno;
    //
    transient private boolean cancellata;
    transient private List<PersonaGiuridica> struttureAttive;
    transient private List<PersonaGiuridica> struttureCancellate;
    transient private List<PersonaGiuridica> subStrutture;
    transient private List<PersonaGiuridica> subStruttureAttive;
    transient private List<PersonaGiuridica> subStruttureCancellate;
    transient private Map<Integer,String> mapSelectStruttureAttive;
    
    transient private int idPadre;
    transient private int idopPersonaGiuridicaTipo;
    transient private int idTipo;
    transient private int idStru;
    transient private int idDipendente;
    transient private Date dataDipendente;
    
    transient private Set<PersonaFisica> dipendentiSub;
    
    transient private List<PersonaFisica> responsabili;
    
    transient private List<Incarico> incarichiAnno;
    transient private List<Incarico> subIncarichiAnno;

    transient private boolean dipartimento;
    
    public PersonaGiuridica() { }

    public Integer getIdPersona() {
        return super.getIdpersona();
    }

    public void setIdPersona(Integer idPersona) {
        super.setIdpersona(idpersona);
    }

    public String getDenominazione() {
            return this.denominazione;
    }

    public void setDenominazione(String denominazione) {
            this.denominazione = denominazione;
    }

    public String getCodice() {
            return codice;
    }

    public void setCodice(String codice) {
            this.codice = codice;
    }

    public String getPercorsoDenominazione() {
            return this.percorsoDenominazione;
    }

    public void setPercorsoDenominazione(String percorsoDenominazione) {
            this.percorsoDenominazione = percorsoDenominazione;
    }

    public OpPersonaGiuridicaStatoEnum getStato() {
            return stato;
    }

    public void setStato(OpPersonaGiuridicaStatoEnum stato) {
            this.stato = stato;
    }

    public Date getDataCambioStato() {
            return dataCambioStato;
    }

    public void setDataCambioStato(Date dataCambioStato) {
            this.dataCambioStato = dataCambioStato;
    }

    public Date getDataInserimento() {
            return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
            this.dataInserimento = dataInserimento;
    }

    public PersonaGiuridicaTipo getOpPersonaGiuridicaTipo() {
            return opPersonaGiuridicaTipo;
    }

    public void setOpPersonaGiuridicaTipo(PersonaGiuridicaTipo opPersonaGiuridicaTipo) {
            this.opPersonaGiuridicaTipo = opPersonaGiuridicaTipo;
    }

    public Set<PersonaAfferenza> getOpPersonaAfferenzas() {
            return this.opPersonaAfferenzas;
    }

    public void setOpPersonaAfferenzas(Set<PersonaAfferenza> opPersonaAfferenzas) {
            this.opPersonaAfferenzas = opPersonaAfferenzas;
    }

    public PersonaGiuridica getOpPersonaGiuridica() {
            return this.opPersonaGiuridica;
    }

    public void setOpPersonaGiuridica(PersonaGiuridica opPersonaGiuridica) {
            this.opPersonaGiuridica = opPersonaGiuridica;
    }

    public Set<PersonaFisica> getDipendentiInternal() {
            return dipendenti;
    }
    
   

    public void setDipendenti(Set<PersonaFisica> dipendenti) {
            this.dipendenti = dipendenti;
    }

    protected Set<PersonaGiuridica> getOpPersonaGiuridicasInternal() {
        return opPersonaGiuridicas;
    }

    protected void setOpPersonaGiuridicas(Set<PersonaGiuridica> opPersonaGiuridicas) {
        this.opPersonaGiuridicas = opPersonaGiuridicas;
    }

        
    public List<PersonaGiuridica> getOpPersonaGiuridicas() {
        List<PersonaGiuridica> sortedOpPersonaGiuridicas = new ArrayList(this.getOpPersonaGiuridicasInternal());
        PropertyComparator.sort(sortedOpPersonaGiuridicas, new MutableSortDefinition("codice",true,true));
        return sortedOpPersonaGiuridicas;
    }
    
    public List<PersonaGiuridica> getOpPersonaGiuridicasAttive() {
    	Set<PersonaGiuridica> attiveOpPersonaGiuridicas = this.getOpPersonaGiuridicasInternal();
    	List<PersonaGiuridica> attiveStrutture = new ArrayList<PersonaGiuridica>();
    	for(PersonaGiuridica struttura : attiveOpPersonaGiuridicas){
    		if (struttura.getStato().equals(OpPersonaGiuridicaStatoEnum.ATTIVO)) attiveStrutture.add(struttura);
    	}
    	PropertyComparator.sort(attiveStrutture, new MutableSortDefinition("denominazione",true,true));
        return attiveStrutture;
    }
    
        
    public List<PersonaGiuridica> getOpPersonaGiuridicasCancellate() {
    	Set<PersonaGiuridica> setStrutture = this.getOpPersonaGiuridicasInternal();
    	List<PersonaGiuridica> deletedStrutture = new ArrayList<PersonaGiuridica>();
    	for(PersonaGiuridica struttura : setStrutture){
    		if (struttura.getStato().equals(OpPersonaGiuridicaStatoEnum.CANCELLATO)) deletedStrutture.add(struttura);
    	}
    	PropertyComparator.sort(deletedStrutture, new MutableSortDefinition("denominazione",true,true));
        return deletedStrutture;
    }

    
	

	// vedi Spring Persistence with Hibernate Beginning p.72 Kindle
    // � un metodo conveniente per le associazioni bidirezionali
    // perch� i riferimenti sono settati su entrambi i lati dell'associazione
    public boolean addDipendenteToPersonaGiuridica(PersonaFisica opPersonaFisica){
        // sul lato azione
        opPersonaFisica.setOpPersonaGiuridica(this);
        // sul lato obiettivo
        return this.getDipendenti().add(opPersonaFisica);
    }
    
    

    public Set<Incarico> getIncarichiInternal() {
        return incarichi;
    }
    
    public List<Incarico> getIncarichi() {
        List<Incarico> sortedIncarichi = new ArrayList(this.getIncarichiInternal());
        PropertyComparator.sort(sortedIncarichi, new MutableSortDefinition("dataInizio",true,true));
        return sortedIncarichi;
    }

    public void setIncarichi(Set<Incarico> incarichi) {
        this.incarichi = incarichi;
    }
    
    
    @Override
    public String toString(){
        return this.denominazione ;
    }

	public Integer getIdPadre() {
		return opPersonaGiuridica.getIdpersona();
	}

	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}

	/*
	public void setResponsabile(OpPersonaFisica responsabile) {
		this.responsabile = responsabile;
	}
*/

	public Integer getIdopPersonaGiuridicaTipo() {
		return idopPersonaGiuridicaTipo;
	}

	public void setIdopPersonaGiuridicaTipo(int idopPersonaGiuridicaTipo) {
		this.idopPersonaGiuridicaTipo = idopPersonaGiuridicaTipo;
	}

	public String getCompetenze() {
		return competenze;
	}

	public void setCompetenze(String competenze) {
		this.competenze = competenze;
	}
    
	public String getCarica() {
		if (this.carica == null) return "";
		else return carica;
	}

	public void setCarica(String carica) {
		this.carica = carica;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public List<PersonaGiuridica> getStruttureAttive() {
		return this.getOpPersonaGiuridicasAttive();
	}

	public List<PersonaGiuridica> getStruttureCancellate() {
		return this.getOpPersonaGiuridicasCancellate();
	}

	public Map<Integer, String> getMapSelectStruttureAttive(PersonaGiuridica dipartimento) {
		// costruisco la map per la select strutture
    	Map<Integer,String> mapSelectStru = new LinkedHashMap<Integer,String>();
    	// prima il dipartimento
    	mapSelectStru.put(dipartimento.idpersona, dipartimento.denominazione);
    	for(PersonaGiuridica stru : dipartimento.getStruttureAttive()){
    		mapSelectStru.put(stru.idpersona, stru.denominazione);
    	}
		return mapSelectStru;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public int getIdStru() {
		return idStru;
	}

	public void setIdStru(int idStru) {
		this.idStru = idStru;
	}

	public boolean isCancellata() {
		if (this.stato.equals(OpPersonaGiuridicaStatoEnum.CANCELLATO)) return true;
		else return false;
	}

	/*
	public List<Obiettivo> getObiettiviPrioritari() {
		return getObiettiviPrio(this.anno);
	}
*/
	
 // ____________ metodi business ____________________________________
    
	//
	public boolean isDipartimento(){
    	//System.out.println("OpPersonaGiuridica.isDipartimento() struttura:"+ this.toString() +" codice Struttura:"+ this.opPersonaGiuridicaTipo.getCodice());
    	//System.out.println("OpPersonaGiuridica.isDipartimento() struttura:"+ this.toString() +" dipartimento:"+ this.opPersonaGiuridicaTipo.getCodice().equals("DIP"));
    	boolean isDip;
    	if (this.opPersonaGiuridicaTipo.getCodice().equals("DIP") || (this.opPersonaGiuridicaTipo.getCodice().equals("AMM")) || (this.opPersonaGiuridicaTipo.getCodice().equals("UFF"))){
    		isDip = true;
    	}
    	else {
    		isDip = false;
    	}
    	return isDip;
    }
	
	//
	
	public PersonaGiuridica getDipartimento() {
		PersonaGiuridica dipartimento = null;
		PersonaGiuridica struttura = this;
		if (this.isDipartimento()) return this;
		//System.out.println("UserServiceImpl.findDipartimentoOfLoggedUser() struttura= "+struttura.getDenominazione());
		int maxLevel = 5;
		while (maxLevel >0 ){
			//System.out.println("UserServiceImpl.findDipartimentoOfLoggedUser() dip?= "+dipartimento.getDenominazione());
			maxLevel--;
			if (struttura.isDipartimento()) {
				dipartimento = struttura;
				break;
			}
			else struttura = struttura.getOpPersonaGiuridica();
		}
		return dipartimento;
	}
	
	
	transient private PersonaFisica responsabile;
	public PersonaFisica getResponsabile() {
		return responsabile;
    }
	public void setResponsabile(PersonaFisica responsabile) {
		this.responsabile = responsabile;
	}

	/*
	transient private OpPersonaFisica ultimoresponsabile;
	public OpPersonaFisica getUltimoResponsabile() {
    	//System.out.println("OpPersonaGiuridica.getResponsabile() struttura:"+ this.toString() +" codice Struttura:"+ this.opPersonaGiuridicaTipo.getCodice());
        OpPersonaFisica dirigStruttura = null;
		// estraggo gli incarichi della struttura
		final List<Incarico> incarichi = this.getIncarichi();
		
		// tra gli incarichi dell'anno estrai il dirigente in base alla scadenza
		if (!incarichi.isEmpty()){
			for (Incarico incarico : incarichi){
						dirigStruttura = incarico.getOpPersonaFisica();
			}
		}
		// estraggo i dirigenti della struttura
		return dirigStruttura;
    }
	*/
    //
    public List<PersonaFisica> getDipendenti() {
    	List<PersonaFisica> sortedDipendenti = new ArrayList(this.getDipendentiInternal());
    	List<PersonaAfferenza> afferenze = new ArrayList(this.getOpPersonaAfferenzas());
    	for(PersonaAfferenza aff : afferenze ){
    		PersonaFisica dip = aff.getOpPersonaFisica();
    		if (!sortedDipendenti.contains(dip)){
    			if  (this.anno >= aff.getAnnoinz() && this.anno <= aff.getAnnofin()){
    				sortedDipendenti.add(dip);
    			}
    		
    		}
    	}
        PropertyComparator.sort(sortedDipendenti, new MutableSortDefinition("order",true,true));
        return sortedDipendenti;
    }
    
    //
    public List<PersonaFisica> getDipendentiAttuali() {
    	List<PersonaFisica> sortedDipendenti = new ArrayList(this.getDipendentiInternal());
        PropertyComparator.sort(sortedDipendenti, new MutableSortDefinition("order",true,true));
        return sortedDipendenti;
    }
    
    //
    public List<PersonaFisica> getDipendentiSub() {
    	List<PersonaFisica> sortedDipendenti = new ArrayList(this.getDipendentiInternal());
    	List<PersonaAfferenza> afferenze = new ArrayList(this.getOpPersonaAfferenzas());
    	for(PersonaAfferenza aff : afferenze ){
    		PersonaFisica dip = aff.getOpPersonaFisica();
    		if (!sortedDipendenti.contains(dip)){
    			if  (this.anno >= aff.getAnnoinz() && this.anno <= aff.getAnnofin()){
    				sortedDipendenti.add(dip);
    			}
    		
    		}
    	}
    	for(PersonaGiuridica pg : this.opPersonaGiuridicas){
    		sortedDipendenti.addAll(pg.getDipendenti());
    	}
        PropertyComparator.sort(sortedDipendenti, new MutableSortDefinition("order",true,true));
        return sortedDipendenti;
    }
    
    public List<PersonaFisica> getDirigenti() {
    	List<PersonaFisica> sortedDirigenti = new ArrayList<PersonaFisica>();
    	List<PersonaFisica> dipendenti = this.getDipendenti();
    	for(PersonaFisica dip : dipendenti ){
    		if (dip.isDirigente()) sortedDirigenti.add(dip);
    	}
        PropertyComparator.sort(sortedDirigenti, new MutableSortDefinition("order",true,true));
        return sortedDirigenti;
    }
    
    public List<PersonaFisica> getDirigentiApicali() {
    	List<PersonaFisica> sortedDirigenti = new ArrayList<PersonaFisica>();
    	List<PersonaFisica> dipendenti = this.getDipendenti();
    	for(PersonaFisica dip : dipendenti ){
    		if (dip.isDirigenteApicale()) sortedDirigenti.add(dip);
    	}
        PropertyComparator.sort(sortedDirigenti, new MutableSortDefinition("order",true,true));
        return sortedDirigenti;
    }
    
    public List<PersonaFisica> getAllDipendenti() {
    	List<PersonaFisica> sortedDipendenti = new ArrayList(this.getDipendenti());
    	for (PersonaGiuridica substr : this.getOpPersonaGiuridicas()){
    		substr.setAnno(anno);
    		sortedDipendenti.addAll(substr.getDipendenti());
    		for (PersonaGiuridica uob : substr.getOpPersonaGiuridicas()){
    			uob.setAnno(anno);
    			sortedDipendenti.addAll(uob.getDipendenti());
    		}
    	}
        PropertyComparator.sort(sortedDipendenti, new MutableSortDefinition("order",true,true));
        return sortedDipendenti;
    }
    
    public List<PersonaFisica> getAllDirigenti() {
    	List<PersonaFisica> sortedDirigenti = new ArrayList<PersonaFisica>();
    	List<PersonaFisica> dipendenti = this.getAllDipendenti();
    	for(PersonaFisica dip : dipendenti ){
    		if (dip.isDirigente()) sortedDirigenti.add(dip);
    	}
        PropertyComparator.sort(sortedDirigenti, new MutableSortDefinition("order",true,true));
        return sortedDirigenti;
    }
    
    /*
    public List<OpPersonaFisica> getDirigentiAnno() {
    	List<OpPersonaFisica> dirigenti = new ArrayList<OpPersonaFisica>();
		for (Incarico inc : this.getSubIncarichiAnno()){
			inc.setAnno(anno);
			dirigenti.add(inc.getOpPersonaFisica());
		}
		PropertyComparator.sort(dirigenti, new MutableSortDefinition("order",true,true));
		return dirigenti;
	}
    
    public List<Incarico> getIncarichiAnno() {
		List<Incarico> lstIncarichiAnno = new ArrayList<Incarico>();
		for (Incarico inc : this.getIncarichi()){
			inc.setAnno(anno);
			if((anno>=inc.getAnnoinz() && anno<=inc.getAnnofin()) && !inc.getOpPersonaGiuridica().isDipartimento()){
				lstIncarichiAnno.add(inc);
			}
		}
		PropertyComparator.sort(lstIncarichiAnno, new MutableSortDefinition("order",true,true));
		return lstIncarichiAnno;
	}
    
    public List<Incarico> getIncarichiApicaliAnno() {
		List<Incarico> lstIncarichiAnno = new ArrayList<Incarico>();
		for (Incarico inc : this.getIncarichi()){
			inc.setAnno(anno);
			if((anno>=inc.getAnnoinz() && anno<=inc.getAnnofin()) && inc.getOpPersonaGiuridica().isDipartimento()){
				lstIncarichiAnno.add(inc);
			}
		}
		PropertyComparator.sort(lstIncarichiAnno, new MutableSortDefinition("order",true,true));
		return lstIncarichiAnno;
	}
    
    public List<Incarico> getSubIncarichiAnno() {
		List<Incarico> lstIncarichiAnno = new ArrayList<Incarico>();
		for (Incarico inc : this.getIncarichi()){
			inc.setAnno(anno);
			if((anno>=inc.getAnnoinz() && anno<=inc.getAnnofin() )&& !inc.getOpPersonaGiuridica().isDipartimento()){
				lstIncarichiAnno.add(inc);
			}
		}	
		for(OpPersonaGiuridica stru : this.getSubStrutture()) {
        	stru.setAnno(anno);
        	lstIncarichiAnno.addAll(stru.getIncarichiAnno());
        }  
		PropertyComparator.sort(lstIncarichiAnno, new MutableSortDefinition("order",true,true));
		return lstIncarichiAnno;
	}
    
    public List<Incarico> getSubIncarichiAnno(int _anno) {
		List<Incarico> lstIncarichiAnno = new ArrayList<Incarico>();
		for (Incarico inc : this.getIncarichi()){
			inc.setAnno(_anno);
			if((_anno>=inc.getAnnoinz() && _anno<=inc.getAnnofin() )&& !inc.getOpPersonaGiuridica().isDipartimento()){
				lstIncarichiAnno.add(inc);
			}
		}	
		for(OpPersonaGiuridica stru : this.getSubStrutture()) {
        	stru.setAnno(_anno);
        	lstIncarichiAnno.addAll(stru.getIncarichiAnno());
        }  
		PropertyComparator.sort(lstIncarichiAnno, new MutableSortDefinition("order",true,true));
		return lstIncarichiAnno;
	}
    */
    public List<PersonaGiuridica> getSubStrutture() {
		List<PersonaGiuridica> lista = new ArrayList<PersonaGiuridica>();
		lista.add(this);
		List<PersonaGiuridica> listaLev1 = this.getOpPersonaGiuridicas();
		if (!listaLev1.isEmpty()) lista.addAll(listaLev1);
		for (PersonaGiuridica subStr : listaLev1){
			if (!subStr.getOpPersonaGiuridicas().isEmpty())
				lista.addAll(subStr.getOpPersonaGiuridicas());
		}
        PropertyComparator.sort(lista, new MutableSortDefinition("codice",true,true));
		return lista;
	}
    
    public List<PersonaGiuridica> getSubStruttureAttive() {
		List<PersonaGiuridica> lista = new ArrayList<PersonaGiuridica>();
		lista.add(this);
		List<PersonaGiuridica> listaLev1 = this.getOpPersonaGiuridicasAttive();
		
		if (!listaLev1.isEmpty()) lista.addAll(listaLev1);
		for (PersonaGiuridica subStr : listaLev1){
			if (!subStr.getOpPersonaGiuridicas().isEmpty())
				lista.addAll(subStr.getOpPersonaGiuridicasAttive());
		}
        PropertyComparator.sort(lista, new MutableSortDefinition("codice",true,true));
		return lista;
	}
    
    public List<PersonaGiuridica> getSubStruttureCancellate() {
		List<PersonaGiuridica> lista = new ArrayList<PersonaGiuridica>();
		lista.add(this);
		List<PersonaGiuridica> listaLev1 = this.getOpPersonaGiuridicasCancellate();
		
		if (!listaLev1.isEmpty()) lista.addAll(listaLev1);
		for (PersonaGiuridica subStr : listaLev1){
			if (!subStr.getOpPersonaGiuridicas().isEmpty())
				lista.addAll(subStr.getOpPersonaGiuridicasCancellate());
		}
        PropertyComparator.sort(lista, new MutableSortDefinition("codice",true,true));
		return lista;
	}

	public int getIdDipendente() {
		return idDipendente;
	}

	public void setIdDipendente(int idDipendente) {
		this.idDipendente = idDipendente;
	}

	public Date getDataDipendente() {
		return dataDipendente;
	}

	public void setDataDipendente(Date dataDipendente) {
		this.dataDipendente = dataDipendente;
	}
    
    public boolean struEquals(PersonaGiuridica other){
    	return (this.idpersona == other.idpersona);
    }
    
} // -------------------------------------------------------


