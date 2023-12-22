package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.exceptions.*;

import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;
import java.util.List;

@Api(value="/mensajes",description="Endpoint to Mensajes Service")
@Path("/mensajes")
public class MensajesService {
    private GameManager gm;

    public MensajesService(){
        this.gm=GameManagerImpl.getInstance();
        if(gm.mensajesSize()==0){
            try{
                Mensaje mensaje = new Mensaje("Este es un mensaje inicial");
                this.gm.addMensaje(mensaje);
            }catch (Throwable e){
                throw new RuntimeException(e);
            }
        }
    }

    @GET
    @ApiOperation(value= "get all mensajes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Mensaje.class,responseContainer = "List"),
    })
    @Path("/todosmen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensajes() {
        List<Mensaje> mensajes=this.gm.getAllMensajes();
        GenericEntity<List<Mensaje>> entity = new GenericEntity<List<Mensaje>>(mensajes) {};

        return Response.status(201).entity(entity).build();
    }
}
