package ista.sistemaClinica.model.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "examen_complementario")
public class ExamenComplementario implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idExa;

    private String tituloExa;
    private Boolean aplicaExa;
    

    private static final long serialVersionUID = 1L;


	public Long getIdExa() {
		return idExa;
	}


	public void setIdExa(Long idExa) {
		this.idExa = idExa;
	}


	public String getTituloExa() {
		return tituloExa;
	}


	public void setTituloExa(String tituloExa) {
		this.tituloExa = tituloExa;
	}


	public Boolean getAplicaExa() {
		return aplicaExa;
	}


	public void setAplicaExa(Boolean aplicaExa) {
		this.aplicaExa = aplicaExa;
	}

}

