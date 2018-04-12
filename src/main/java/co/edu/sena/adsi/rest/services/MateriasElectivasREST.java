/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.MateriasElectivas;
import co.edu.sena.adsi.jpa.sessions.MateriasElectivasFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author adsi1261718
 */
@Path("materiasElectivas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MateriasElectivasREST {
    
     @EJB
    private MateriasElectivasFacade materiasElectivasEJB;
    
    @GET
    public List<MateriasElectivas> findAll(){
        return materiasElectivasEJB.findAll();
    }
    @GET
    @Path("{id}")
    public MateriasElectivas findBye(@PathParam("id")Integer id){
        return materiasElectivasEJB.find(id);
        
}
    @POST
    public Response create(MateriasElectivas materiasElectivas){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (materiasElectivasEJB.findUsuarioByNombre(materiasElectivas.getNombre()) == null) {
                
            materiasElectivasEJB.create(materiasElectivas);
            return Response.status(Response.Status.CREATED).entity(gson.toJson("La materia se creó correctamente!")).build();
            
             } else {
                return Response.status(Response.Status.CONFLICT).entity(gson.toJson("La materia ya se encuentra registrada!")).build();
            }
        }catch (Exception e) {
            Logger.getLogger(MateriasElectivasREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el materia!.")).build();
        }
    }
    
    @PUT
    @Path("{id}")
    public Response edit(@PathParam("id") Integer id, MateriasElectivas materiasElectivas) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            materiasElectivasEJB.edit(materiasElectivas);
            return Response.status(Response.Status.CREATED)
                    .entity(gson.toJson("La materia se Actualizo Correctamente"))
                    .build();
        } catch (Exception e) {
            System.out.println("Err" + e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson("Error al Actualizar La Materia"))
                    .build();
        }
    }
    
    /* @DELETE
    public Response salir(
            @QueryParam("id")Integer id,
            @QueryParam("cupos") int cupos) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            MateriasElectivas materiasElectivas = materiasElectivasFacade.find(id);
            Usuarios usuarios = usuariosEJB.find(id_usuarios);
            if (carro == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("No se ha encontrado el carro con placa " + placa)).build();
            } else if (horaSalida <= carro.getHoraLlegada()) {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("la hora de salida no puede ser menor o igual a " + carro.getHoraLlegada())).build();
            } else {
                Puestos puesto = carro.getPuestos();
                puesto.setPlacaCarro(null);
                double pago = parqueadero.getTarifa() * (horaSalida - carro.getHoraLlegada());
                parqueadero.setCaja(parqueadero.getCaja() + pago);
                parqueaderoEJB.edit(parqueadero);
                puestoEJB.edit(puesto);
                carrosFacade.remove(carro);
                return Response.status(Response.Status.OK).entity(gson.toJson("Valor a pagar: " + pago)).build();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error: " + e)).build();
        }
 
}*/
}
