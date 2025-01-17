package organiko.domain.personaFisica;

import java.util.*;

import javax.persistence.*;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import organiko.domain.incarico.Incarico;
import organiko.domain.persona.Persona;
import organiko.domain.personaAfferenza.PersonaAfferenza;
import organiko.domain.personaFisicaTipo.PersonaFisicaTipo;
import organiko.domain.personaGiuridica.PersonaGiuridica;

/**
 * The persistent class for the op_persona_fisica database table.
 * 
 */
@Entity
@Table(name = "op_persona_fisica")
public class PersonaFisica extends Persona {			
    private static final long serialVersionUID = 1L;
    public enum OpPersonaFisicaSessoEnum {M, F}
    public enum OpPersonaFisicaStatoEnum {
        ATTIVO, 
		CANCELLATO, // Cancellato e non visibile in anagrafica
		TRASFERITO, 
		PENSIONTATO, 
		LICENZIATO, 
		NASCOSTO // Visibile solo da particolari utenti
    }
	
	// l'Id viene ereditato da Persona
    private String cognome;
    private String nome;
    private String matricola;
    private String datanascita;
    private String codicefiscale;    
    @Enumerated
	private OpPersonaFisicaStatoEnum stato;

	// bi-directional many-to-one association to OpPersonaFisicaTipo
	@ManyToOne(fetch = FetchType.EAGER) // non modificare senn� sposta dipendente va in errore
	@JoinColumn(name = "op_persona_fisica_tipo_idop_persona_fisica_tipo")
	private PersonaFisicaTipo opPersonaFisicaTipo;
	// bi-directional many-to-one association to OpPersonaAfferenza
	@OneToMany(mappedBy = "opPersonaFisica", fetch = FetchType.EAGER)
	private Set<PersonaAfferenza> opPersonaAfferenzas;  
	// bi-directional many-to-one association to OpPersonaGiuridica
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "op_persona_giuridica_idpersona")
	private PersonaGiuridica opPersonaGiuridica;    
	@OneToMany(mappedBy = "personaFisica")
	private Set<Incarico> incarichi = new HashSet<Incarico>();
	
	// ----------- transient
    transient private String stringa;
    transient private String order;
    transient private int idOpPersonaGiuridica;
    transient private int idopPersonaFisicaTipo;
    transient private int anno;
    transient private int pesoAssegnazioni;
    transient private double punteggioAssegnazioni;
    transient private Date dataCambioStruttura;
    transient private String cognomeNome;
    transient private List<Incarico> incarichiAnno;
    transient private boolean dipartimento;
    transient private Incarico incaricoValutazione;
    transient private String categoria;
    transient private int annoIniz;

    
    // ------------------------ metodi ------------
	public PersonaFisica() {
	}
        
        public Integer getIdPersona() {
            return super.getIdpersona();
        }

        public void setIdPersona(Integer idPersona) {
            super.setIdpersona(idpersona);
        }

	

	public String getCognome() {
		return this.cognome.toUpperCase().trim();
	}

	public void setCognome(String cognome) {
		this.cognome = cognome.toUpperCase().trim();
	}


	public String getMatricola() {
		if   (this.matricola.length()==4) return ("0"+this.matricola.trim());
		else return this.matricola.trim();
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public OpPersonaFisicaStatoEnum getStato() {
		return this.stato;
	}

	public void setStato(OpPersonaFisicaStatoEnum stato) {
		this.stato = stato;
	}

	

	public PersonaFisicaTipo getOpPersonaFisicaTipo() {
		return opPersonaFisicaTipo;
	}

	public void setOpPersonaFisicaTipo(PersonaFisicaTipo opPersonaFisicaTipo) {
		this.opPersonaFisicaTipo = opPersonaFisicaTipo;
	}

	public Set<PersonaAfferenza> getOpPersonaAfferenzas() {
		return this.opPersonaAfferenzas;
	}

	public void setOpPersonaAfferenzas(
			Set<PersonaAfferenza> opPersonaAfferenzas) {
		this.opPersonaAfferenzas = opPersonaAfferenzas;
	}

	public PersonaGiuridica getOpPersonaGiuridica() {
		return opPersonaGiuridica;
	}

    public void setOpPersonaGiuridica(PersonaGiuridica opPersonaGiuridica) {
		this.opPersonaGiuridica = opPersonaGiuridica;
    }


   
        
    @Override
    public String toString(){
        return this.opPersonaFisicaTipo.getDescrizione() + " " 
                + this.opPersonaFisicaTipo.getLivello()+ " " 
                + this.cognome + " " + this.nome;
    }

    public String getStringa() {
        return this.toString();
    }
    
    
    public String getDatanascita() {
		return datanascita;
	}

	public void setDatanascita(String datanascita) {
		this.datanascita = datanascita;
	}

	public String getCodicefiscale() {
		return codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public String getOrder(){
        return this.cognome + " " + this.nome + " " + this.opPersonaFisicaTipo.getDescrizione() ;
    }

    public String getCognomeNome(){
        return this.cognome + " " + this.nome ;
    }
    
	public int getIdOpPersonaGiuridica() {
		return idOpPersonaGiuridica;
	}

	public void setIdOpPersonaGiuridica(int idOpPersonaGiuridica) {
		this.idOpPersonaGiuridica = idOpPersonaGiuridica;
	}

	public int getIdopPersonaFisicaTipo() {
		return idopPersonaFisicaTipo;
	}

	public void setIdopPersonaFisicaTipo(int idopPersonaFisicaTipo) {
		this.idopPersonaFisicaTipo = idopPersonaFisicaTipo;
	}

	
	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	

	public Date getDataCambioStruttura() {
		return dataCambioStruttura;
	}

	public void setDataCambioStruttura(Date dataCambioStruttura) {
		this.dataCambioStruttura = dataCambioStruttura;
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
    
	// ____________________ business methods ---------------------
	
	public PersonaGiuridica getDipartimento() {
		return opPersonaGiuridica.getDipartimento();
	}
    
	public boolean isDirigente(){
		int idTipo = this.getOpPersonaFisicaTipo().getIdopPersonaFisicaTipo();
		if ((idTipo==13) || // 13 Dirigente 
			(idTipo==26) 	// 26 Dirigente Esterno
		)	return true;
		else return false;
	}
	
	public boolean isDirigenteApicale(){
		int idTipo = this.getOpPersonaFisicaTipo().getIdopPersonaFisicaTipo();
		if ((idTipo==14) || // 14 Dirigente Generale 
			(idTipo==24) ||  // 24 Segretario Generale
			(idTipo==28) || // 28 Ragioniere Generale
			(idTipo==30) // 30 Avvocato Generale
		)	return true;
		else return false;
	}

	public Incarico getIncaricoValutazione() {
		return incaricoValutazione;
	}

	public void setIncaricoValutazione(Incarico incaricoValutazione) {
		this.incaricoValutazione = incaricoValutazione;
	}
	
	/// DDD
	
	public Integer getPfTipoID() { return opPersonaFisicaTipo.getIdopPersonaFisicaTipo(); }
	public Integer getPgID() { return opPersonaGiuridica.getIdPersona(); }

	public String getCategoria() {
		return opPersonaFisicaTipo.getCategoria();
	}

	public int getAnnoIniz() {
		int anno = 2012;
		for(PersonaAfferenza aff: this.opPersonaAfferenzas){
			int annofinale = aff.getAnnofin(); 
        	if (anno <annofinale ) anno = annofinale; 
        }
		return anno;
	}

} // ------------------------------------------------------------

