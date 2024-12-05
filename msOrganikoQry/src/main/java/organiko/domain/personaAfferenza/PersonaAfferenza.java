package organiko.domain.personaAfferenza;


import javax.persistence.*;

import organiko.domain.personaFisica.PersonaFisica;
import organiko.domain.personaGiuridica.PersonaGiuridica;

import java.util.Calendar;
import java.util.Date;


@Entity
@Table(name="op_persona_afferenza")
public class PersonaAfferenza {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idop_persona_afferenza")
	private Integer idopPersonaAfferenza;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="data_inizio")
	private Date dataInizio;

    

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="date_fine")
	private Date dataFine;

	//bi-directional many-to-one association to OpPersonaFisica
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="op_persona_fisica_idpersona")
	private PersonaFisica opPersonaFisica;

	//bi-directional many-to-one association to OpPersonaGiuridica
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="op_persona_giuridica_idpersona")
	private PersonaGiuridica opPersonaGiuridica;

	
	//
    transient private int annoinz;
    transient private int annofin;
    //
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
	
	
	
	
    public PersonaAfferenza() {
    }

	public Integer getIdopPersonaAfferenza() {
		return this.idopPersonaAfferenza;
	}

	public void setIdopPersonaAfferenza(Integer idopPersonaAfferenza) {
		this.idopPersonaAfferenza = idopPersonaAfferenza;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dateFine) {
		this.dataFine = dateFine;
	}

	public PersonaFisica getOpPersonaFisica() {
		return this.opPersonaFisica;
	}

	public void setOpPersonaFisica(PersonaFisica opPersonaFisica) {
		this.opPersonaFisica = opPersonaFisica;
	}
	
	public PersonaGiuridica getOpPersonaGiuridica() {
		return this.opPersonaGiuridica;
	}

	public void setOpPersonaGiuridica(PersonaGiuridica opPersonaGiuridica) {
		this.opPersonaGiuridica = opPersonaGiuridica;
	}
	
	@Override
	public boolean equals(Object o){
		boolean check = false;
		if(o instanceof PersonaAfferenza){
			if(((PersonaAfferenza)o).idopPersonaAfferenza.equals(idopPersonaAfferenza)){
					check = true;
			}
		}
		return check;
	}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.idopPersonaAfferenza != null ? this.idopPersonaAfferenza.hashCode() : 0);
        return hash;
    }
}


