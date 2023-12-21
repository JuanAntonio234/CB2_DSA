package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Jugador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameManagerTest {

    private GameManager gm;

    @Before
    public void setUp() throws JugadorYaExisteException, NotAnEmailException, FaltanDatosException {
        this.gm = GameManagerImpl.getInstance();
        if (gm.JugadoresSize()==0) {
            this.gm.addJugador("Antonio","Fernanditox_47@gmail.com","SweetP2");
            this.gm.addJugador("Lobi","malasia.2010@gmail.com","Perico23");
            this.gm.addJugador("Fernando33","brasil.2005@gmail.com","33?");
        }
    }

    @Test
    public void addJugadorTest() throws JugadorYaExisteException, NotAnEmailException, FaltanDatosException {

        Assert.assertEquals(3,gm.JugadoresSize());
        try {
            this.gm.addJugador("Antonio","Fernanditox_47@gmail.com","SweetP2");
            Assert.fail("Se esperaba que lanzara JugadorYaExisteException");
        } catch (JugadorYaExisteException e) {
            Assert.assertEquals("Ese jugador ya existe (el email y el usuario tienen que ser únicos)", e.getMessage());
        }

        try {
            this.gm.addJugador("Anton","Fernanditox_47","SweetP2");
            Assert.fail("Se esperaba que lanzara NotAnEmailException");
        } catch (NotAnEmailException e) {
            Assert.assertEquals("No es un email", e.getMessage());
        }

        try {
            this.gm.addJugador(null,"Fernanditox_47@gmail.com","SweetP2");
            Assert.fail("Se esperaba que lanzara FaltanDatosException");
        } catch (FaltanDatosException e) {
            Assert.assertEquals("Faltan datos", e.getMessage());
        }

    }

    @Test
    public void logJugadorTest() throws UserNotFoundException, FaltanDatosException, ErrorCredencialesException {

        this.gm.logJugador("Antonio", "SweetP2");
        Assert.assertEquals(null, null);

        try {
            this.gm.logJugador(null,"SweetP2");
            Assert.fail("Se esperaba que lanzara FaltanDatosException");
        } catch (FaltanDatosException e) {
            Assert.assertEquals("Faltan datos", e.getMessage());
        }

        try {
            this.gm.logJugador("sdf","SweetP2");
            Assert.fail("Se esperaba que lanzara UserNotFoundException");
        } catch (UserNotFoundException e) {
            Assert.assertEquals("El usuario no existe", e.getMessage());
        }

        try {
            this.gm.logJugador("Antonio","SwetP2");
            Assert.fail("Se esperaba que lanzara WrongPasswordException");
        } catch (ErrorCredencialesException e) {
            Assert.assertEquals("La contraseña no es correcta", e.getMessage());
        }


    }

    @Test
    public void TestConsultarPuntuaciones() throws UserNotFoundException, JugadorYaExisteException, NotAnEmailException, FaltanDatosException {
        Jugador j = new Jugador("Pablo", "pablo@yahoo.com", "nada");
        this.gm.addJugador(j);
        int msg1 = gm.consultarPuntuacion(j.getUsername());
        Assert.assertEquals(100, msg1);

        try {
            gm.consultarPuntuacion("sda");
            Assert.fail("Se esperaba que lanzara UserNotFoundException");
        } catch (UserNotFoundException e) {
            Assert.assertEquals("El usuario no existe", e.getMessage());
        }
    }
}
