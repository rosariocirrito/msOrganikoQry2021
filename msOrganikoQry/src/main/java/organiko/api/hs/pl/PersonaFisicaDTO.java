package organiko.api.hs.pl;


import javax.persistence.*;

import organiko.domain.personaFisica.PersonaFisica;
import organiko.domain.personaFisica.PersonaFisica.OpPersonaFisicaStatoEnum;

/**
 * The persistent class for the op_persona_fisica database table.
 * 
 */

public class PersonaFisicaDTO {
	public static final long serialVersionUID = 1L;
    
	public final Integer idPersona;
	public final String cognome;
	public final String nome;
	public final String stringa;
	public final String matricola;
    @Enumerated
    public final OpPersonaFisicaStatoEnum stato;
    public final Integer pfTipoID;
	public final Integer pgID;
	public final boolean dirigente;
	public final boolean dirigenteApicale;
	public final String categoria;
    
    // ------------------------ metodi ------------
	public PersonaFisicaDTO(PersonaFisica pf) {
		this.idPersona = pf.getIdPersona();
		this.cognome = pf.getCognome();
		this.nome = pf.getNome();
		this.matricola = pf.getMatricola();
		this.stato = pf.getStato();
		this.pfTipoID = pf.getPfTipoID();
		this.pgID = pf.getPgID();
		this.dirigente = pf.isDirigente();
		this.dirigenteApicale = pf.isDirigenteApicale();
		this.stringa = pf.getStringa();
		this.categoria = pf.getCategoria();
	}
        
   
} // ------------------------------------------------------------
