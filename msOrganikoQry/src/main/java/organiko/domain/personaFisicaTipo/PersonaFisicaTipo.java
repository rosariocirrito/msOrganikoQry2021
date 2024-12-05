package organiko.domain.personaFisicaTipo;


import java.util.List;
import javax.persistence.*;

import organiko.domain.personaFisica.PersonaFisica;


/**
 * The persistent class for the op_persona_fisica_tipo database table.
 * 
 */
@Entity
@Table(name="op_persona_fisica_tipo")
public class PersonaFisicaTipo {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idop_persona_fisica_tipo")
	private Integer idopPersonaFisicaTipo;

	private String codice;

	private String descrizione;

	private String livello;
	
	private String categoria;

	transient private String liv_descr;
	
	//bi-directional many-to-one association to OpPersonaFisica
	@OneToMany(mappedBy="opPersonaFisicaTipo", fetch=FetchType.LAZY)
	private List<PersonaFisica> opPersonaFisicas;

    public PersonaFisicaTipo() {
    }

	public Integer getIdopPersonaFisicaTipo() {
		return this.idopPersonaFisicaTipo;
	}

	public void setIdopPersonaFisicaTipo(Integer idopPersonaFisicaTipo) {
		this.idopPersonaFisicaTipo = idopPersonaFisicaTipo;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getLivello() {
		return this.livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

	public List<PersonaFisica> getOpPersonaFisicas() {
		return this.opPersonaFisicas;
	}

	public void setOpPersonaFisicas(List<PersonaFisica> opPersonaFisicas) {
		this.opPersonaFisicas = opPersonaFisicas;
	}

	public String getLiv_descr() {
		return  this.descrizione +"_"+ this.livello;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	
	
}

