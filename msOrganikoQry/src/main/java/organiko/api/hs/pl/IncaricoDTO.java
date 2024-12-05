package organiko.api.hs.pl;


//import java.time.LocalDate;
import java.util.Date;

import organiko.domain.incarico.Incarico;


public class IncaricoDTO {
	
	// ------------------- campi db ---------------------------------------
	public static final long serialVersionUID = 1L;


    public final Integer idIncarico;
    public final Date dataInizio;
    public final Date dataFine;
    public final Integer pfID;
    public final Integer pgID;
    public final boolean interim;
    
    public final String responsabile;
    public final String denominazioneStruttura;
    public final String competenzeStruttura;
    public final int idDept;
    public final String denominazioneDipartimento;
    public final boolean incaricoDipartimentale;
    public final String carica;
   
    public IncaricoDTO(Incarico inc){
    	this.idIncarico = inc.getIdIncarico();
        this.dataInizio =inc.getDataInizio();
        this.dataFine = inc.getDataFine();
        this.pfID = inc.getIdPF();
        this.pgID = inc.getIdPG();
        this.interim =inc.isInterim();
        
        this.responsabile = inc.getResponsabile();
        this.denominazioneStruttura = inc.getDenominazioneStruttura();
        this.competenzeStruttura = inc.getCompetenzeStruttura();
        this.idDept = inc.getIdDept();
        this.denominazioneDipartimento = inc.getDenominazioneDipartimento();
        this.incaricoDipartimentale = inc.isIncaricoDipartimentale();
        this.carica = inc.getCarica();
    }
	
} // ----------------

