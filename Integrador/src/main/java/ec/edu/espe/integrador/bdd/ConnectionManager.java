/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Integrador
 * Creado: 10/11/2019 - 14:35:41
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.integrador.bdd;

import ec.edu.espe.integrador.utils.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de conexion a la base de datos
 *
 * @author Paspuel-Torres
 */
public class ConnectionManager {

    private static final Logger LOG = Logger.getLogger(ConnectionManager.class.getName());
    private static ConnectionManager instancia;
    private String usuario;
    private String clave;
    private String cadena;

    private ConnectionManager(String cadena, String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
        this.cadena = cadena;
    }

    public static ConnectionManager getInstance() {
        if (ConnectionManager.instancia == null) {
            ConnectionManager.init();
        }
        return ConnectionManager.instancia;
    }

    public static void init() {
        Config config = new Config();
        try {
            String usuarioF = config.obtenerPropiedad("usuario");
            String claveF = config.obtenerPropiedad("clave");
            String driverF = config.obtenerPropiedad("driver");
            String cadenaF = config.obtenerPropiedad("cadena");
            try {
                Class.forName(driverF);
                ConnectionManager.instancia = new ConnectionManager(cadenaF, usuarioF, claveF);
            } catch (ClassNotFoundException ex) {
                LOG.log(Level.SEVERE, "Error al instanciar conexion", ex);
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error al obtener datos de conexion", ex);
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(cadena, usuario, clave);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al obtener conexion", ex);
            return null;
        }
    }

    public void releaseConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al cerrar conexion", ex);
        }
    }
}
