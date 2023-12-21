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


@Api(value = "/avatares", description = "Endpoint to Avatar Service")
@Path("/avatares")
public class AvatarService {

    private GameManager gm;

    public AvatarService() { //throws NotAnEmailException, FaltanDatosException, JugadorYaExisteException, UserNotFoundException {
        this.gm = GameManagerImpl.getInstance();
        if (gm.AvataresSize()==0) {
            try {
                this.gm.addAvatar("AX-21",23,100,10,50);
                this.gm.addAvatar("ZE-32",25,150,20,25);
                this.gm.addAvatar("DC-25",29,110,50,100);
                this.gm.addAvatar("RS-6",30,200,100,100);

            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }


}
