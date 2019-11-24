/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Integrador
 * Creado: 09-nov-2019 - 17:19:10
 * Modificado: 09-nov-2019 - 17:19:10 
 *
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 * 
 */
package ec.edu.espe.distribuidas.protocolpi;
/**
 * Clase CabeceraPosIntegrador. Creacion de la clase CabeceraPosIntegrador con
 * metodos set y get para la implementación del protocolo
 *
 * @author Paspuel-Torres
 */
public class CabeceraPosIntegrador {

    private String tipoMensaje;
    private String dispositivo;
    private Integer codigoMensaje;
    private String fecha;
    private Integer longitudCuerpo;

    public CabeceraPosIntegrador() {
    }

    public CabeceraPosIntegrador(String tipoMensaje, String dispositivo, Integer cod_mensaje, String fecha, Integer longitudCuerpo) {
        this.tipoMensaje = tipoMensaje;
        this.dispositivo = dispositivo;
        this.codigoMensaje = cod_mensaje;
        this.fecha = fecha;
        this.longitudCuerpo = longitudCuerpo;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Integer getCodigoMensaje() {
        return codigoMensaje;
    }

    public void setCodigoMensaje(Integer codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getLongitudCuerpo() {
        return longitudCuerpo;
    }

    public void setLongitudCuerpo(Integer longitudCuerpo) {
        this.longitudCuerpo = longitudCuerpo;
    }

}
