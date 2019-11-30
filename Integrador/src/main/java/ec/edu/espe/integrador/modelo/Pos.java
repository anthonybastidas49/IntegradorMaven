/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Integrador
 * Creado: 09/11/2019 - 22:30:53
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.integrador.modelo;

import java.util.Objects;

/**
 * Clase modelo de Pos. Relacionada con la Tabla POS 
 *
 * @author Paspuel-Torres
 */
public class Pos {
    private Integer codigo;
    private String codigoDispositivo;
    private String pin;
    private String estado;

    public Pos() {
    }

    public Pos(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodigoDispositivo() {
        return codigoDispositivo;
    }

    public void setCodigoDispositivo(String codigoDispositivo) {
        this.codigoDispositivo = codigoDispositivo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pos other = (Pos) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }  
}
