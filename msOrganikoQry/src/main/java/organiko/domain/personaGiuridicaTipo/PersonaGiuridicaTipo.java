package organiko.domain.personaGiuridicaTipo;

import javax.persistence.*;

@Entity
@Table(name="op_persona_giuridica_tipo")
public class PersonaGiuridicaTipo {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idop_persona_giuridica_tipo")
	private Integer idopPersonaGiuridicaTipo;

	private String codice;

	private String nome;
	private transient int idTipo;

	//bi-directional many-to-one association to OpPersonaGiuridica
	//@OneToMany(mappedBy="opPersonaGiuridicaTipo")
	//private List<OpPersonaGiuridica> opPersonaGiuridicas;

    public PersonaGiuridicaTipo() {
    }

	public Integer getIdopPersonaGiuridicaTipo() {
		return this.idopPersonaGiuridicaTipo;
	}

	public void setIdopPersonaGiuridicaTipo(Integer idopPersonaGiuridicaTipo) {
		this.idopPersonaGiuridicaTipo = idopPersonaGiuridicaTipo;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/*
	public List<OpPersonaGiuridica> getOpPersonaGiuridicas() {
		return this.opPersonaGiuridicas;
	}

	public void setOpPersonaGiuridicas(List<OpPersonaGiuridica> opPersonaGiuridicas) {
		this.opPersonaGiuridicas = opPersonaGiuridicas;
	}
	*/
	
	@Override
	public boolean equals(Object o){
		boolean check = false;
		if(o instanceof PersonaGiuridicaTipo){
			if(((PersonaGiuridicaTipo)o).idopPersonaGiuridicaTipo.equals(idopPersonaGiuridicaTipo)){
				check = true;
			}
		}
		return check;
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.idopPersonaGiuridicaTipo != null ? this.idopPersonaGiuridicaTipo.hashCode() : 0);
        return hash;
    }

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
    
    
}
