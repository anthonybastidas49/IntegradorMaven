/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Corebanking
 * Creado: 09-nov-2019 - 02:35:00
 * Modificado: 09-nov-2019 - 03:40:30
 *
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 * 
 */
package ec.edu.espe.integrador.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de lectura de propiedades de conexion a la base de datos.
 *
 * @author Paspuel-Torres
 */
public class Config {

    private static final Logger LOG = Logger.getLogger(Config.class.getName());

    public Config() {

    }

    public String obtenerPropiedad(String atributo) {
        String result = null;
        try {
            Properties prop = new Properties();
            InputStream is = null;
            try {
                is = new FileInputStream("bdd.properties");
                prop.load(is);
                result = prop.getProperty(atributo);

            } catch (IOException ioe) {
                LOG.log(Level.SEVERE, "Error al leer el archivo properties", ioe);
            }

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al instanciar Properties", e);
            result = null;
        }
        return result;
    }

}
