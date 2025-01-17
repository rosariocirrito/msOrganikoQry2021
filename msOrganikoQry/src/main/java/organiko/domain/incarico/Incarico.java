package organiko.domain.incarico;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import organiko.domain.personaFisica.PersonaFisica;
import organiko.domain.personaGiuridica.PersonaGiuridica;

@Entity
@Table(name="incarico")
public class Incarico {
	
	// ------------------- campi db ---------------------------------------
	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Integer idIncarico;
	    

	    @Temporal( TemporalType.TIMESTAMP)
	    private Date dataInizio;

	    @Temporal( TemporalType.TIMESTAMP)
	    private Date dataFine;
	    //
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "idPF")
		private PersonaFisica personaFisica; 
	    
	    transient private Integer idPF;
	    
	    //
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "idPG")
	    private PersonaGiuridica personaGiuridica;
	    transient private Integer idPG;
	    
	    private boolean interim;
	    
	    // ----------------- transient ---------------------------
	    transient private int idDip;
	    transient private int idStruttura;
	    transient private boolean nuovo;
	    transient private String stringa;
	    //
	    transient private int annoinz;
	    transient private int annofin;
	    private transient int anno;
	    
	    //transient private OpPersonaFisica responsabile;
	    
	    transient private String order;
	    transient private List<Incarico> listaIncarichiDaClonare;
	    transient private int idIncaricoDaClonare;
	    
	    // campi transient per IncaricoDTO
	    transient String responsabile;
	    transient String denominazioneStruttura;
	    transient String competenzeStruttura;
	    transient int idDept;
	    transient String denominazioneDipartimento;
	    transient boolean incaricoDipartimentale;
	    transient String carica;
	    
	    // ---------------- metodi --------------
	    public int getAnnoinz() {
	    	Calendar inizio = Calendar.getInstance();
	    	if (null!=this.dataInizio) inizio.setTime(dataInizio);
			return inizio.get(Calendar.YEAR);
		}
		public int getAnnofin() {
			Calendar fine = Calendar.getInstance();
	    	if (null!=this.dataFine) fine.setTime(dataFine);
			return fine.get(Calendar.YEAR);
		}
	    //
	    
	    
	    public int getIdStruttura() {
	        return idStruttura;
	    }

	    public void setIdStruttura(int idStruttura) {
	        this.idStruttura = idStruttura;
	    }
	    public int getIdDip() {
	        return idDip;
	    }

	    public void setIdDip(int idDip) {
	        this.idDip = idDip;
	    }

	    public boolean isNuovo() {
	        return (this.idIncarico == null);
	    }

	    
	    public Incarico() {    }

	    public Integer getIdIncarico() {return idIncarico;}
	    public void setIdIncarico(Integer idIncarico) {this.idIncarico = idIncarico;}
	    
	    public Date getDataInizio() {return this.dataInizio;}
	    public void setDataInizio(Date datainizio) {this.dataInizio = datainizio;}

	    public Date getDataFine() {return this.dataFine;}
	    public void setDataFine(Date dataFine) {this.dataFine = dataFine;}

	    
	    
	    public int getAnno() {
			return anno;
		}
		public void setAnno(int anno) {
			this.anno = anno;
		}
		
		
		public PersonaFisica getOpPersonaFisica() {return this.personaFisica;}
	    public void setOpPersonaFisica(PersonaFisica opPersonaFisica) {
	        this.personaFisica = opPersonaFisica;
	    }
	    
	    public PersonaGiuridica getOpPersonaGiuridica() {return this.personaGiuridica;}
	    public void setOpPersonaGiuridica(PersonaGiuridica opPersonaGiuridica) {
	        this.personaGiuridica = opPersonaGiuridica;
	    }
	   
		
	

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Incarico other = (Incarico) obj;
        if (this.idIncarico != other.idIncarico && (this.idIncarico == null || !this.idIncarico.equals(other.idIncarico))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.idIncarico != null ? this.idIncarico.hashCode() : 0);
        return hash;
    }

    
	@Override
	public String toString(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String str = "ID: "+this.idIncarico +" da: "+ sdf.format(dataInizio) + " a: "+ sdf.format(dataFine);
		/*
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  String text1 = this.dataInizio.format(formatter);
		  LocalDate parsedDataInizio = LocalDate.parse(text1, formatter);
		  String text2 = this.dataFine.format(formatter);
		  LocalDate parsedDataFine = LocalDate.parse(text2, formatter);
		String str = "ID: "+this.idIncarico +" da: "+ parsedDataInizio + " a: "+ parsedDataFine;
		if (interim) str += " INTERIM";
		*/
		return str;
	}
	
    
	public boolean isInterim() {
		return interim;
	}
	public void setInterim(boolean interim) {
		this.interim = interim;
	}
	
	
	public String getStringa() {
	/*
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String str = opPersonaGiuridica.getCodice() + " / " +opPersonaFisica.getCognomeNome() + 
				" da: "+ sdf.format(dataInizio) + " a: "+ sdf.format(dataFine);
		if (interim) str += " INTERIM";*/
		return this.toString();
	}
	
	
	
	//public OpPersonaFisica getResponsabile() {
    	//System.out.println("OpPersonaGiuridica.getResponsabile() struttura:"+ this.toString() +" codice Struttura:"+ this.opPersonaGiuridicaTipo.getCodice());
	//	return this.opPersonaFisica;
    //}
	
	public String getOrder() {
		String str = this.personaGiuridica.getDenominazione()+" -> "+this.personaFisica.getOrder();
		return order;
	}
	
	
	public List<Incarico> getListaIncarichiDaClonare() {
		return listaIncarichiDaClonare;
	}
	public void setListaIncarichiDaClonare(List<Incarico> listaIncarichiDaClonare) {
		this.listaIncarichiDaClonare = listaIncarichiDaClonare;
	}
	public int getIdIncaricoDaClonare() {
		return idIncaricoDaClonare;
	}
	public void setIdIncaricoDaClonare(int idIncaricoDaClonare) {
		this.idIncaricoDaClonare = idIncaricoDaClonare;
	}
	
	// DDD
	public Integer getIdPF() {return this.personaFisica.getIdPersona();}
	public void setIdPF(Integer idPF) {	this.idPF = idPF;}
	public Integer getIdPG() {return this.personaGiuridica.getIdPersona();}
	public void setIdPG(Integer idPG) {this.idPG = idPG;}
	public String getResponsabile() {
		return responsabile;
	}
	public void setResponsabile(String responsabile) {
		this.responsabile = responsabile;
	}
	public String getDenominazioneStruttura() {
		return denominazioneStruttura;
	}
	public void setDenominazioneStruttura(String denominazioneStruttura) {
		this.denominazioneStruttura = denominazioneStruttura;
	}
	public String getCompetenzeStruttura() {
		return competenzeStruttura;
	}
	public void setCompetenzeStruttura(String competenzeStruttura) {
		this.competenzeStruttura = competenzeStruttura;
	}
	public int getIdDept() {
		return idDept;
	}
	public void setIdDept(int idDept) {
		this.idDept = idDept;
	}
	public String getDenominazioneDipartimento() {
		return denominazioneDipartimento;
	}
	public void setDenominazioneDipartimento(String denominazioneDipartimento) {
		this.denominazioneDipartimento = denominazioneDipartimento;
	}
	public boolean isIncaricoDipartimentale() {
		return incaricoDipartimentale;
	}
	public void setIncaricoDipartimentale(boolean incaricoDipartimentale) {
		this.incaricoDipartimentale = incaricoDipartimentale;
	}
	public String getCarica() {
		return carica;
	}
	public void setCarica(String carica) {
		this.carica = carica;
	}
	public PersonaFisica getPersonaFisica() {
		return personaFisica;
	}
	public void setPersonaFisica(PersonaFisica personaFisica) {
		this.personaFisica = personaFisica;
	}
	public PersonaGiuridica getPersonaGiuridica() {
		return personaGiuridica;
	}
	public void setPersonaGiuridica(PersonaGiuridica personaGiuridica) {
		this.personaGiuridica = personaGiuridica;
	}
	
	
	
	
	
	
	
	// DTO
	
	
	
} // ----------------


