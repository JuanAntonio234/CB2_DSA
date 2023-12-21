package edu.upc.dsa;

import java.util.*;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;
import edu.upc.dsa.util.Verificar;


public class GameManagerImpl implements GameManager {

    private static edu.upc.dsa.GameManager instance;
    protected List<Partida> partidas;
    protected Map<String, Jugador> jugadores;
    protected List<Avatar> avatares;
    protected List<Mapa> mapas;
    protected List<Tienda> productos;

    final static Logger logger = Logger.getLogger(GameManagerImpl.class);
    private GameManagerImpl() {
        this.partidas = new LinkedList<>();
        this.jugadores = new HashMap<>();
        this.avatares = new LinkedList<>();
        this.mapas = new LinkedList<>();
        this.productos = new LinkedList<>();
    }

    public static edu.upc.dsa.GameManager getInstance() {
        if (instance==null)
            instance = new GameManagerImpl();
        return instance;
    }

    public int PartidaSize() {      // Número de partidas creadas
        int ret = this.partidas.size();
        logger.info("Game size " + ret);
        return ret;
    }
    public int AvataresSize(){      // Número de avatares disponibles
        int ret = this.avatares.size();
        logger.info("Avatares size " + ret);
        return ret;
    }

    public int JugadoresSize() {    // Número de jugadores
        int ret = this.jugadores.size();
        logger.info("Jugadores size " + ret);
        return ret;
    }

    //          Organización
    //   1        ---> Jugador Manager
    //   2        ---> Tienda Manager
    //   3        ---> Partida Manager
    //   4        ---> Avatar Manager


    // Jugador Manager

    public Jugador addJugador(Jugador jugador) throws NotAnEmailException, FaltanDatosException, JugadorYaExisteException {
        logger.info("new Jugador " + jugador.getUsername());
        logger.info(jugador.getUsername() + jugador.getMail() + jugador.getPassword());
        if (jugador.getMail() == null  || jugador.getUsername() == null || jugador.getPassword() == null){
            logger.info("Faltan datos");
            throw new FaltanDatosException();
        }
        for (Jugador j : this.findAllJugadores()){
            if (j.getMail().equals(jugador.getMail()) || j.getUsername().equals(jugador.getUsername())){
                logger.info("Ese jugador ya existe (el email y el usuario tienen que ser únicos)");
                throw new JugadorYaExisteException();
            }
        }
        if (Verificar.esDireccionCorreoValida(jugador.getMail()) == false){
            logger.info("No es un email");
            throw new NotAnEmailException();
        }
        else{
            this.jugadores.put(jugador.getUsername(), jugador);
            logger.info("new Jugador added");
            return jugador;
        }
    }

    public Jugador addJugador(String username, String mail, String pasword) throws NotAnEmailException, FaltanDatosException, JugadorYaExisteException { return this.addJugador(new Jugador(username, mail, pasword)); }

    public CredencialesRespuesta logJugador(String username, String password) throws FaltanDatosException, ErrorCredencialesException {
        CredencialesRespuesta respuesta = new CredencialesRespuesta();
        if(username == null || password == null){
            logger.info("Faltan datos");
            throw new FaltanDatosException();
        }

        Jugador j = jugadores.get(username);
        if (j == null){
            logger.info("El usuario no existe");
            throw new ErrorCredencialesException();
        } else if (j.getPassword().equals(password)){
            logger.info("Login del jugador " + username);
            respuesta.setSuccess(true);
            return respuesta;
        } else{
            logger.info("Usuario o contraseña errónea");
            throw new ErrorCredencialesException();
        }
    }


    public List<Jugador>  findAllJugadores(){
        List<Jugador> lista = new ArrayList<Jugador>(jugadores.values());
        return lista;
    }



    public CredencialesRespuesta updateUsername(String username, String newUsername, String password) throws ErrorCredencialesException {
        Jugador j = jugadores.get(username);
        CredencialesRespuesta r = new CredencialesRespuesta();

        if (j == null){
            logger.info("El usuario no existe");
            throw new ErrorCredencialesException();
        }
        else if (j.getPassword().equals(password)) {

            j.setUsername(newUsername);
            jugadores.remove(j);
            jugadores.remove(j);
            jugadores.put(newUsername, j);
            logger.info("El usuario " + username + " quiere cambiar su nombre a " + newUsername);
            logger.info("El usuario cambió su username a " + newUsername);
            r.setSuccess(true);
            return r;

        }else{
            logger.info("Contraseña incorrecta");
            throw new ErrorCredencialesException();
        }
    }

    public CredencialesRespuesta updatePassword(String user, String newPass, String password) throws ErrorCredencialesException {
        Jugador j = jugadores.get(user);
        CredencialesRespuesta respuesta = new CredencialesRespuesta();

        if (j == null){
            logger.info("El usuario no existe");
            throw new ErrorCredencialesException();
        }
        else if (j.getPassword().equals(password)) {
            j.setPassword(newPass);
            logger.info("El usuario " + user + " quiere cambiar su contraseña a " + newPass);
            logger.info("El usuario cambió su contraseña");
            respuesta.setSuccess(true);
            return respuesta;

        }else{
            logger.info("Contraseña incorrecta");
            throw new ErrorCredencialesException();
        }
    }


    public CredencialesRespuesta deleteUser(String username) throws UserNotFoundException {
        CredencialesRespuesta respuesta = new CredencialesRespuesta();
        Jugador j = jugadores.get(username);
        if (j == null){
            logger.info("El usuario no existe");
            throw new UserNotFoundException();
        }
        else{
            this.jugadores.remove(username);
            logger.info("El usuario " + j.getUsername() +" quiere borrar su perfil");
            logger.info("El usuario borró la cuenta");
            respuesta.setSuccess(true);
            return respuesta;
        }
    }


    public void deleteUser(String username, String password) throws UserNotFoundException{
        CredencialesRespuesta respuesta = new CredencialesRespuesta();
        Jugador j = jugadores.get(username);
        if (j == null){
            logger.info("El usuario no existe");
            throw new UserNotFoundException();
        }
        else{
            if (j.getPassword().equals(password)){
                this.jugadores.remove(username);
                logger.info("El usuario " + j.getUsername() +" quiere borrar su perfil");
                logger.info("El usuario borró la cuenta");
                respuesta.setSuccess(true);
            }
        }
    }



   /* public int consultarPuntuacion(String usuario) throws UserNotFoundException {
        logger.info("El jugador con id " + usuario + " quiere consultar su puntuación");
        Jugador j = jugadores.get(usuario);
        if (j == null){
            logger.info("El usuario no existe");
            throw new UserNotFoundException();
        }
        else{
            int puntos = j.getPoints();
            logger.info("El usuario tiene "+ puntos + " puntos");
            return puntos;
        }
    }*/







    // Que pasa si yo ya tengo una partida guardada de antes??
    // Cómo se tendría que inciar?
//    public void startPartida(Partida p, Jugador j, Mapa m, Avatar a) {        // El jugador inicicia una partida
//        if(p.GetMapId() == -1){     // Un id negativo implica que la partida es nueva
//            logger.info(j.getUserName() + " inicia partida en el nivel 1");
//            p.SetNivel(1);
//            p.SetMapaId(m.getId());
//            j.setPoints(50);
//            this.Mapas.add(m);
//            this.Jugadores.add(j);
//        }
//        /* else{
//            logger.info(j.GetUserName() + " inicia partida en el nivel" + p.GetNivel());
//            p.SetNivel(1);
//            p.SetMapaId(m.GetId());
//            j.SetPoints(50);
//            this.Mapas.add(m);
//            this.Jugadores.add(j);
//        } */
//
//    }

    public Jugador getJugador(String username) throws UserNotFoundException {
        logger.info("getUser("+username+")");
        Jugador j = jugadores.get(username);
        if (j == null){
            logger.info("El usuario no existe");
            throw new UserNotFoundException();
        }
        else{
            logger.info("getUser("+username+"): "+ j);
            return j;
        }
    }


    // Tienda Manager

    public Tienda addProducto(Tienda producto) throws ProductoYaExisteException, FaltanDatosException{
        logger.info("new product " + producto.getNombre());
        for(Tienda p : this.findAllProductos()) {
            if(p.getNombre().equals(producto.getNombre()))
                throw new ProductoYaExisteException();
        }
        if(producto.getNombre() == null || producto.getDescription() == null || producto.getEfect() < 1 || producto.getEfectType() < 0  || producto.getEfectType() > 3 || producto.getPrecio() < 0)
            throw new FaltanDatosException();
        else{
            this.productos.add(producto);
            logger.info("new producto added");
            return producto;
        }
    }
    public Tienda addProducto(String image,int precio, String nombre, String descripcion, int efect_type, int efect) throws ProductoYaExisteException, FaltanDatosException {return this.addProducto(new Tienda(image,precio, nombre, descripcion, efect_type,efect));}

    public Tienda getProducto(String nombre) throws ProductoNotFoundException{
        logger.info("getProducto("+nombre+")");

        for (Tienda p: this.productos) {
            if (p.getNombre().equals(nombre)) {
                logger.info("getProducto("+nombre+"): "+p);

                return p;
            }
        }

        logger.error("not found " + nombre);
        throw new ProductoNotFoundException();
    }

    public void comprarProducto(String Pnombre, String usrnm) throws ProductoNotFoundException, CapitalInsuficienteException, UserNotFoundException{
        logger.info("Entramos en la función de comprar");
        try {
            Tienda p = this.getProducto(Pnombre);
            Jugador j = this.getJugador(usrnm);
            int precio = p.getPrecio();
            if(j.getEurillos() < precio) {
                logger.error("Estas tieso hermano, el producto " +p.getNombre()+" cuesta " + p.getPrecio() +" y tu tienes "+ j.getEurillos()+" eurillos");
                throw new CapitalInsuficienteException();
            }
            else{
                logger.info(usrnm + " se ha comprado " + Pnombre);
                j.setEurillos((j.getEurillos() - precio));
                if(p.getEfectType() == 0) {
                    logger.info("Se ha incrementado la salud");
                    this.increaseHealth(usrnm, p.getEfect());
                }
                if(p.getEfectType() == 1) {
                    logger.info("Se ha incrementado el daño");
                    this.increaseDamage(usrnm, p.getEfect());
                }
                if(p.getEfectType() == 2) {
                    logger.info("Se ha incrementado la velocidad");
                    this.increaseSpeed(usrnm, p.getEfect());
                }
                if(p.getEfectType() == 3) {
                    logger.info("El jugador se ha hecho invisible...");
                    this.invisibility(usrnm);
                }
                logger.info("EL SALDO ACTUAL ES:"+ j.getEurillos());

            }
        }
        catch (UserNotFoundException e) {
            logger.error("User not found");
            throw new UserNotFoundException();
        }
        catch (ProductoNotFoundException e){
            logger.error("Producto no encontrado");
            throw new ProductoNotFoundException();
        }
    }
    public List<Tienda> deleteProducto(Tienda producto) throws ProductoNotFoundException, FaltanDatosException {
        if(producto.getNombre() != null || producto.getDescription() != null || producto.getEfect() >= 1 || producto.getEfectType() >= 0 || producto.getEfectType() <= 3){
            logger.info("delete Producto" + producto.getNombre() + ")");
            int i = 0;
            for (Tienda p : this.productos) {
                if (p.getNombre().equals(producto.getNombre())) {
                    this.productos.remove(i);
                    return this.productos;
                }
                i++;
            }
            throw new ProductoNotFoundException();
        }
        else {
            throw new FaltanDatosException();
        }
    }
    public int TiendasSize(){
        int ret = this.productos.size();
        logger.info("Productos size " + ret);
        return ret;
    }

    public List<Tienda> findAllProductos(){return this.productos;}

    public void increaseDamage(String jugadorUsername, int damage){
        Jugador jugador=jugadores.get(jugadorUsername);
        boolean encontrado = false;
        if(jugador !=null){
            String av =jugador.getAvatar();
            for(Avatar avatar : this.avatares){
                if(avatar.getNombre().equals(av)){
                    int damg=avatar.getDamg()+ damage;
                    avatar.setDamg(damg);
                    encontrado = true;
                    logger.info("El jugador " + jugadorUsername + " ha incrementado su daño");
                }
            }
        }else{
            logger.warn("No se encontró al jugador con username "+jugadorUsername);
        }
        if(!encontrado)
            logger.warn("El jugador"+ jugadorUsername+" no tiene un avatar actual");
    }

    public void increaseHealth(String jugadorUsername, int health){
        Jugador jugador=jugadores.get(jugadorUsername);
        boolean encontrado = false;
        if(jugador!=null) {
            String av = jugador.getAvatar();
            for(Avatar avatar : this.avatares){
                if (avatar.getNombre().equals(av)) {
                    int hlth = avatar.getHealth() + health;
                    avatar.setHealth(hlth);
                    logger.info("El jugador " + jugadorUsername + " ha incrementado su vida");
                    encontrado = true;
                }
            }
        }
        else{
            logger.warn("No se encontró al jugador con username " + jugadorUsername);
        }
        if(!encontrado)
            logger.warn("El jugador " + jugadorUsername + " no tiene un avatar actual");
    }
    public void increaseSpeed(String jugadorUsername, int speed){
        Jugador jugador=jugadores.get(jugadorUsername);
        boolean encontrado = false;
        if(jugador!=null){
            String av=jugador.getAvatar();
            for(Avatar avatar : this.avatares){
                if(avatar.getNombre().equals(av)){
                    int spd=avatar.getSpeed() + speed;
                    avatar.setSpeed(spd);
                    encontrado = true;
                    logger.info("El jugador " + jugadorUsername + " ha incrementado su velocidad");
                }
            }
        }else{
            logger.warn("No se encontró al jugador con username " + jugadorUsername);
        }
        if(!encontrado)
            logger.warn("El jugador "+jugadorUsername+" no tiene un avatar actual");
    }
    public void invisibility(String jugadorUsername){
        Jugador jugador=jugadores.get(jugadorUsername);
        boolean encontrado = false;
        if(jugador!=null){
            String av = jugador.getAvatar();
            for(Avatar avatar : this.avatares){
                if(avatar.getNombre().equals(av)){
                    avatar.setVisible(1);
                    encontrado = true;
                    logger.info("El jugador " + jugadorUsername + " se ha hecho invisible");
                }
            }
        }else{
            logger.warn("No se encontró al jugador con username " + jugadorUsername);
        }
        if(!encontrado)
            logger.warn("El jugador "+jugadorUsername+" no tiene un avatar actual");
    }


    // Partida Manager

    public Partida addPartida(Partida partida) throws PartidaYaExisteException, FaltanDatosException{
        logger.info("Se quiere crear una nueva partida");
        if(partida.getPlayer() == null|| partida.getNivel() > 2 || partida.getDificultad() < 0|| partida.getMapId() == null){
            logger.warn("Faltan datos de la partida");
            throw new FaltanDatosException();
        }
        for(Partida p : this.partidas) {
            if (p.getPartidaId().equals(partida)) {
                logger.warn("La partida ya existe, no puede haber un jugador en dos partidas diferentes");
                throw new PartidaYaExisteException();
            }
        }
        logger.info("Partida añadida correctamente");
        this.partidas.add(partida);
        return partida;
    }

    public Partida addPartida(int dif, String player, String idMapa) throws PartidaYaExisteException, FaltanDatosException{return this.addPartida(new Partida(dif, player,idMapa));}

    public List<Partida> consultarPartidas(String username) throws UserNotFoundException {
        logger.info("El usuario " + username + " quiere consultar una lista con sus partidas");
        List<Partida> par = new LinkedList<Partida>();
        Jugador j = jugadores.get(username);
        if (j == null){
            logger.info("El usuario no existe");
            throw new UserNotFoundException();
        }
        else{
            for (Partida p:partidas){
                if (p.getPlayer().equals(username)){
                    par.add(p);
                }
            }
            if (par.isEmpty()) {
                logger.info("El usuario aun no jugó ninguna partida");
                return Collections.emptyList();
            }
            else {
                return par;
            }
        }
    }

    public int cambiarDificultad(String player, int newdif) throws PartidaNotFoundException, MismaDificultadException{
        logger.info("El jugador "+player+" quiere cambiar la dificultad de su partida");
        boolean encontrado = false;
        for(Partida p : this.partidas){
            if(p.getPlayer().equals(player)) {
                if(p.getDificultad() == newdif) {
                    logger.warn("La dificultad seleccionada es la misma que se está jugando");
                    throw new MismaDificultadException();
                }else {
                    logger.info("Dificultad cambiada correctamente");
                    p.setDificultad(newdif);
                    encontrado = true;
                }
            }
        }
        if(!encontrado)
            return 1;
        else{
            logger.warn("El jugador " +player+ " no está en ninguna partida");
            throw new PartidaNotFoundException();
        }
    }

    // Avatar Manager

    public Avatar addAvatar(Avatar avatar) throws AvatarYaExisteException, FaltanDatosException{
        logger.info("new Avatar added " + avatar.getNombre());
        logger.info(avatar.getNombre() + " Salud: " + avatar.getHealth() +" Daño: " +avatar.getDamg() + " Velocidad: " + avatar.getSpeed());
        boolean igual = false;
        if (avatar.getNombre() == null  || avatar.getHealth() == 0 || avatar.getDamg() == 0 || avatar.getSpeed() == 0){
            logger.info("Faltan datos");
            throw new FaltanDatosException();
        }
        for (Avatar a : this.findAllAvatares()){
            if (a.getNombre().equals(avatar.getNombre()) ){
                logger.info("Ese jugador ya existe (el email y el usuario tienen que ser únicos)");
                igual = true;
                throw new AvatarYaExisteException();
            }
        }
        this.avatares.add(avatar);
        logger.info("new Avatar added");
        return avatar;
    }

    public Avatar addAvatar(String nombre, int idArma, int health, int damg, int speed) throws AvatarYaExisteException, FaltanDatosException{return this.addAvatar(new Avatar(nombre, idArma, health, damg, speed));}

    public List<Avatar> findAllAvatares(){return this.avatares;}
}