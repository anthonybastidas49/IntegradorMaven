/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Protocolo
 * Creado: 09-nov-2019 - 17:19:10
 * Modificado: 09-nov-2019 - 17:19:10 
 *
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 * 
 */
package ec.edu.espe.distribuidas.protocolpi.banco;

/**
 * Clase CabeceraIntegradorBanco. Creacion de la clase CabeceraIntegradorBanco
 * con metodos set y get para la implementación del protocolo
 *
 * @author Paspuel
 * @author Torres
 */
public class CabeceraIntegradorBanco {

    private String tipoMensaje;
    private Integer codigoMensaje;
    private String fecha;
    private Integer longitudCuerpo;

    public CabeceraIntegradorBanco() {
    }

    public CabeceraIntegradorBanco(String tipoMensaje, Integer codigoMensaje, String fecha, Integer longitudCuerpo) {
        this.tipoMensaje = tipoMensaje;
        this.codigoMensaje = codigoMensaje;
        this.fecha = fecha;
        this.longitudCuerpo = longitudCuerpo;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
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
