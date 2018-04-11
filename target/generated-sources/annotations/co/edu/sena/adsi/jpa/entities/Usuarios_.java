package co.edu.sena.adsi.jpa.entities;

import co.edu.sena.adsi.jpa.entities.MateriasElectivas;
import co.edu.sena.adsi.jpa.entities.Roles;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-11T12:27:05")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> apellidos;
    public static volatile SingularAttribute<Usuarios, Boolean> estado;
    public static volatile ListAttribute<Usuarios, Roles> rolesList;
    public static volatile SingularAttribute<Usuarios, String> direccion;
    public static volatile SingularAttribute<Usuarios, String> documento;
    public static volatile SingularAttribute<Usuarios, String> codigoUniversitario;
    public static volatile SingularAttribute<Usuarios, Integer> id;
    public static volatile ListAttribute<Usuarios, MateriasElectivas> materiasElectivasList;
    public static volatile SingularAttribute<Usuarios, String> email;
    public static volatile SingularAttribute<Usuarios, String> nombres;
    public static volatile SingularAttribute<Usuarios, String> contraseña;

}