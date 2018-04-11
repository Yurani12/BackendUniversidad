package co.edu.sena.adsi.jpa.entities;

import co.edu.sena.adsi.jpa.entities.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-11T11:10:46")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile SingularAttribute<Roles, String> descripcion;
    public static volatile ListAttribute<Roles, Usuarios> usuariosList;
    public static volatile SingularAttribute<Roles, String> id;

}