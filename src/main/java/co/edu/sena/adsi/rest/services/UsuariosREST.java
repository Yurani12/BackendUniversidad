/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.Roles;
import co.edu.sena.adsi.jpa.entities.Usuarios;
import co.edu.sena.adsi.jpa.sessions.UsuariosFacade;
import co.edu.sena.adsi.rest.auth.DigestUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author adsi1261718
 */
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosREST {
    
    @EJB
    private UsuariosFacade usuariosEJB;
    
    
    //@RolesAllowed({"A"})
    @GET
    public List<Usuarios> findAll(){
        return usuariosEJB.findAll();
    }
    
       
    /**
     * Obtiene todos los usuarios con rol ESTUDIANTE
     *
     * @return lista de ESTUDIANTES
     */
    @GET
    @Path("estudiantes")
    public List<Usuarios> findAllUsuariosByRol() {
        return usuariosEJB.findAllUsuariosByRol("E");
    }

     /***
     * 
     * Obtiene todos los usuarios con rol administrador
     * @return Lista de administrador
      */
    
    @GET
    @Path("adminstrador")
    public List<Usuarios> finAllUsuariosByRol(){
        return usuariosEJB.findAllUsuariosByRol("A");
    }
    
    
          
    /**
     * Obtiene todos los usuarios con rol ESTUDIANTE
     *
     * @return lista de Profesores
     */
    @GET
    @Path("profesor")
    public List<Usuarios> finAllUsuariosByProfesor(){
        return usuariosEJB.findAllUsuariosByProfesor("P");
    }
    
    @GET
    @Path("{id}")
    public Usuarios findBye(
    @PathParam("id")Integer id){
        return usuariosEJB.find(id);
    
}
    /*
    * Registro de usuario
    */
     @POST
    public Response create(Usuarios usuario) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (usuariosEJB.findUsuarioByDocumento(usuario.getDocumento()) == null) {
                usuario.setPassword(DigestUtil.cifrarPassword(usuario.getPassword()));
                if (usuariosEJB.findUsuarioByEmail(usuario.getEmail()) == null) {
                    usuario.setEstado(Boolean.TRUE);
                    usuariosEJB.create(usuario);
                    return Response.status(Response.Status.CREATED).entity(gson.toJson("El usuario se registro correctamente!")).build();

                } else {
                    return Response.status(Response.Status.CONFLICT).entity(gson.toJson("El email ya esta registrado!")).build();
                }

            } else {
                return Response.status(Response.Status.CONFLICT).entity(gson.toJson("El número de documento ya esta registrado!")).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error de persistencia!")).build();
        }
    }
    
      /**
     * Crea ADMIN
     * @param usuarios
     * @return 
      */
    @POST
    @Path("administrador")
    public Response createAdmin(Usuarios usuarios) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (usuariosEJB.findUsuarioByEmail(usuarios.getEmail()) == null) {
                if (usuariosEJB.findUsuarioByDocumento(usuarios.getDocumento()) == null) {
                    
                    usuarios.setPassword(DigestUtil.cifrarPassword(usuarios.getPassword()));
                    usuarios.setRolesList(new ArrayList<Roles>());
                    usuarios.getRolesList().add(new Roles("A"));
                    usuariosEJB.create(usuarios);
                    return Response.status(Response.Status.CREATED).entity(gson.toJson("El administrador se registro correctamente!")).build();

                
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El número de documento ya se encuentra registrado!.")).build();
                }
           } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El email ya se encuentra registrado!.")).build();
            }
        } catch (Exception e) {
            Logger.getLogger(UsuariosREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al crear el administrador!.")).build();
        }
    }
    
 
      /**
     * Crea ESTUDIANTE
     * @param usuarios
     * @return 
      */
    @POST
    @Path("estudiante")
    public Response createEstudiante(Usuarios usuarios) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (usuariosEJB.findUsuarioByEmail(usuarios.getEmail()) == null) {
                if (usuariosEJB.findUsuarioByDocumento(usuarios.getDocumento()) == null) {
                    
                    usuarios.setPassword(DigestUtil.cifrarPassword(usuarios.getPassword()));
                    usuarios.setRolesList(new ArrayList<Roles>());
                    usuarios.getRolesList().add(new Roles("E"));
                    usuariosEJB.create(usuarios);
                    return Response.status(Response.Status.CREATED).entity(gson.toJson("El estudiante se registro correctamente!")).build();

                
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El número de documento ya se encuentra registrado!.")).build();
                }
           } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El email ya se encuentra registrado!.")).build();
            }
        } catch (Exception e) {
            Logger.getLogger(UsuariosREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al crear el estudiante!.")).build();
        }
    }
    
    
    
      /**
     * Crea profesor
     * @param usuarios
     * @return 
      */
    @POST
    @Path("profesor")
    public Response createProfesor(Usuarios usuarios) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (usuariosEJB.findUsuarioByEmail(usuarios.getEmail()) == null) {
                if (usuariosEJB.findUsuarioByDocumento(usuarios.getDocumento()) == null) {
                    
                    usuarios.setPassword(DigestUtil.cifrarPassword(usuarios.getPassword()));
                    usuarios.setRolesList(new ArrayList<Roles>());
                    usuarios.getRolesList().add(new Roles("P"));
                    usuariosEJB.create(usuarios);
                    return Response.status(Response.Status.CREATED).entity(gson.toJson("El Profesor se registro correctamente!")).build();

                
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El número de documento ya se encuentra registrado!.")).build();
                }
           } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El email ya se encuentra registrado!.")).build();
            }
        } catch (Exception e) {
            Logger.getLogger(UsuariosREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al crear el Profesor!.")).build();
        }
    }
    
    @PUT
    @Path("{id}")
    public Response edit(@PathParam("id") Integer id, Usuarios usuarios) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            usuariosEJB.edit(usuarios);
            return Response.status(Response.Status.CREATED)
                    .entity(gson.toJson("El Usuario se Actualizo Correctamente"))
                    .build();
        } catch (Exception e) {
            System.out.println("Err" + e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson("Error al Actualizar El Usuario"))
                    .build();
        }
    }
    
}
