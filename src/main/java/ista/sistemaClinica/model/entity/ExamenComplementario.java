package ista.sistemaClinica.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "examen_complementario")
public class ExamenComplementario implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idExa;

    private String tituloExa;
    private Boolean aplicaExa;

    @Lob
    @JsonIgnore
    private byte[] archivoPdf; // Campo para almacenar el archivo PDF
    // Solo se almacena la ruta o nombre del archivo PDF
    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    // Campo adicional para el tipo de contenido (ej. application/pdf)
    @Column(name = "tipo_contenido")
    private String tipoContenido;

    // Campo para el tamaño del archivo en bytes
    @Column(name = "tamaño_archivo")
    private Long tamañoArchivo;

    private static final long serialVersionUID = 1L;

    // Getters y Setters
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

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public Long getTamañoArchivo() {
        return tamañoArchivo;
    }

    public void setTamañoArchivo(Long tamañoArchivo) {
        this.tamañoArchivo = tamañoArchivo;
    }
    public byte[] getArchivoPdf() {
        return archivoPdf;
    }

    public void setArchivoPdf(byte[] archivoPdf) {
        this.archivoPdf = archivoPdf;
    }
}