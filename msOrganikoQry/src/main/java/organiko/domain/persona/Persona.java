package organiko.domain.persona;

import java.util.List;
import javax.persistence.*;

import organiko.domain.personaFisica.PersonaFisica;


@Entity
@Table(name="persona")
@Inheritance(strategy=InheritanceType.JOINED)
public class Persona {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer idpersona;

    public Persona() { }

	public Integer getIdpersona() {	return this.idpersona; }

	public void setIdpersona(Integer idpersona) {this.idpersona = idpersona;}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idpersona == null) ? 0 : idpersona.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (idpersona == null) {
			if (other.idpersona != null)
				return false;
		} else if (!idpersona.equals(other.idpersona))
			return false;
		return true;
	}


	
}


