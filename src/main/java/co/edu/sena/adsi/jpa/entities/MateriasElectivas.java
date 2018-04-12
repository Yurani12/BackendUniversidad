/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.jpa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yurani
 */
@Entity
@Table(name = "materias_electivas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriasElectivas.findAll", query = "SELECT m FROM MateriasElectivas m")
    , @NamedQuery(name = "MateriasElectivas.findById", query = "SELECT m FROM MateriasElectivas m WHERE m.id = :id")
    , @NamedQuery(name = "MateriasElectivas.findByNombre", query = "SELECT m FROM MateriasElectivas m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "MateriasElectivas.findByDescripcion", query = "SELECT m FROM MateriasElectivas m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "MateriasElectivas.findByCupos", query = "SELECT m FROM MateriasElectivas m WHERE m.cupos = :cupos")})
public class MateriasElectivas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cupos")
    private int cupos;
     
    /*@JoinTable(name = "usuarios_materias", joinColumns = {
        @JoinColumn(name = "id_materias_electivas", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_usuarios", referencedColumnName = "id")})
    @ManyToMany*/
   
    
    @ManyToMany(mappedBy = "materiasElectivasList")
    private List<Usuarios> usuariosList;

    public MateriasElectivas() {
    }

    public MateriasElectivas(Integer id) {
        this.id = id;
    }

    public MateriasElectivas(Integer id, String nombre, int cupos) {
        this.id = id;
        this.nombre = nombre;
        this.cupos = cupos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriasElectivas)) {
            return false;
        }
        MateriasElectivas other = (MateriasElectivas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.adsi.jpa.entities.MateriasElectivas[ id=" + id + " ]";
    }
    
}
