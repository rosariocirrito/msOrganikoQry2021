package organiko.api.hs.pl;


import java.util.*;

import organiko.domain.personaGiuridica.PersonaGiuridica;
import organiko.domain.personaGiuridica.PersonaGiuridica.OpPersonaGiuridicaStatoEnum;



public class PersonaGiuridicaDTO {
	
    public static final long serialVersionUID = 1L;
    
    public final Integer idPersona;
    public final String denominazione;
    public final String codice;
    public final String competenze;
    public final OpPersonaGiuridicaStatoEnum stato;
    public final Date dataCambioStato;
    public final Date dataInserimento;
    public final Integer pgTipoID;
    public final Integer pgPadreID;
    public final Integer pgDeptID;
    public final boolean dipartimento;
    public final boolean cancellata;
    public final String carica;

    public PersonaGiuridicaDTO(PersonaGiuridica pg) {
    	this.idPersona = pg.getIdPersona();
    	this.denominazione = pg.getDenominazione();
    	this.codice = pg.getCodice()  ;
    	this.competenze = pg.getCompetenze()  ;
    	this.stato = pg.getStato()  ;
    	this.dataCambioStato = pg.getDataCambioStato()  ;
    	this.dataInserimento  = pg.getDataInserimento()  ;
    	this.pgTipoID  = pg.getOpPersonaGiuridicaTipo().getIdopPersonaGiuridicaTipo()  ;
    	this.pgPadreID  = pg.getOpPersonaGiuridica().getIdPersona()  ;
    	this.dipartimento = pg.isDipartimento();
    	this.cancellata = pg.isCancellata();
    	this.pgDeptID = pg.getDipartimento().getIdPersona(); 
    	this.carica = pg.getCarica()  ;
    }

	

   
} // -------------------------------------------------------

