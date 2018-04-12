
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
    public List<MateriasElectivas> findAll() {
        return materiasElectivasEJB.findAll();
    }

    @GET
    @Path("{id}")
    public MateriasElectivas findBye(@PathParam("id") Integer id) {
        return materiasElectivasEJB.find(id);

    }

    @POST
    public Response create(MateriasElectivas materiasElectivas) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (materiasElectivasEJB.findUsuarioByNombre(materiasElectivas.getNombre()) == null) {

                materiasElectivasEJB.create(materiasElectivas);
                return Response.status(Response.Status.CREATED).entity(gson.toJson("La materia se creó correctamente!")).build();

            } else {
                return Response.status(Response.Status.CONFLICT).entity(gson.toJson("La materia ya se encuentra registrada!")).build();
            }
        } catch (Exception e) {
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

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
             MateriasElectivas materiasElectivas = materiasElectivasEJB.find(id);
        materiasElectivasEJB.remove(materiasElectivas);
        return Response.status(Response.Status.CREATED)
                    .entity(gson.toJson("La materia se Elimino Correctamente!"))
                    .build();
        }catch (Exception e) {
            System.out.println("Err" + e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson("Error al Eliminar La Materia"))
                    .build();
        } }
    
    /*
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
    MateriasElectivas materiasElectivas = materiasElectivasEJB.find(id);
        materiasElectivasEJB.remove(materiasElectivas);
    }
    */
}
