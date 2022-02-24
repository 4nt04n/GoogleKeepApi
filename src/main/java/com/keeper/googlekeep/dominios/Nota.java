package com.keeper.googlekeep.dominios;

import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "notas")
public class Nota implements  Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long idUsuario;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private String tags;
    private Date recordatorio;


    public  Nota() {

    }

    public Nota(Long id, Long idUsuario, String titulo, String descripcion,Date fecha, String tags,Date recordatorio) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha=fecha;
        this.tags = tags;
        this.recordatorio=recordatorio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(Date recordatorio) {
        this.recordatorio = recordatorio;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id);
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", tags='" + tags + '\'' +
                ", recordatorio=" + recordatorio +
                '}';
    }

    @Override
    protected Nota clone() throws CloneNotSupportedException {

            Nota nota = null;
            try {
                nota = (Nota) super.clone();
            } catch (CloneNotSupportedException e) {
                nota = new Nota(
                        this.getId(), this.getIdUsuario(),this.getTitulo(),this.getDescripcion(),this.getFecha(),this.getTags(),this.getRecordatorio());
            }

            return nota;
        }
    }

