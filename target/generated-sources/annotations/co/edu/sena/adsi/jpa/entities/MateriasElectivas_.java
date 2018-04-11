package co.edu.sena.adsi.jpa.entities;

import co.edu.sena.adsi.jpa.entities.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-11T10:58:41")
@StaticMetamodel(MateriasElectivas.class)
public class MateriasElectivas_ { 

    public static volatile SingularAttribute<MateriasElectivas, String> descripcion;
    public static volatile ListAttribute<MateriasElectivas, Usuarios> usuariosList;
    public static volatile SingularAttribute<MateriasElectivas, Usuarios> idProfesor;
    public static volatile SingularAttribute<MateriasElectivas, Integer> id;
    public static volatile SingularAttribute<MateriasElectivas, Integer> cupos;
    public static volatile SingularAttribute<MateriasElectivas, String> nombre;

}