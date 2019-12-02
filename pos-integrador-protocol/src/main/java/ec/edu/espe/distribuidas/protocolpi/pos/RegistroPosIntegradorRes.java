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
package ec.edu.espe.distribuidas.protocolpi.pos;

/**
 * Clase RegistroPosIntegradorRes. Creacion de la clase RegistroPosIntegradorRes
 * con metodos set y get para la implementación del protocolo Registro en
 * Response
 *
 * @author Paspuel
 * @author Torres
 */
public class RegistroPosIntegradorRes extends MensajeProtocolo {

    private Integer codigoSesion;
    private String estado;

    public RegistroPosIntegradorRes() {
    }

    public RegistroPosIntegradorRes(CabeceraPosIntegrador cabecera) {
        super();
        super.cabecera = cabecera;
    }

    public RegistroPosIntegradorRes(CabeceraPosIntegrador cabecera, Integer codigoSesion, String estado) {
        super();
        super.cabecera = cabecera;
        this.codigoSesion = codigoSesion;
        this.estado = estado;
    }

    public Integer getCodigoSesion() {
        return codigoSesion;
    }

    public void setCodigoSesion(Integer codigoSesion) {
        this.codigoSesion = codigoSesion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public void parse(String text) throws ProtocolParserException{
        String partesRegistro[]=text.split(Protocol.SEPARADOR);
        if(partesRegistro.length!=7){
            throw new ProtocolParserException(ErrorCodesParser.CAMPOS_INSUFICIENTES,
                    "El mensaje recibido tiene menos campos de los necesarios para parsear la cabecera. Campos recibidos:" + text.length());
        }else{
            //this.setCabecera(new CabeceraPosIntegrador(partesRegistro[0],partesRegistro[1],Integer.parseInt(partesRegistro[2]),partesRegistro[3], Integer.parseInt(partesRegistro[4])));
            try{
                this.setCodigoSesion(Integer.parseInt(partesRegistro[5]));
            }catch(Exception ex){
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en codigo sesión. Codigo sesion recibido: " + partesRegistro[5].toString());
            }
            if(!partesRegistro[6].equals("ACT") || !partesRegistro[6].equals("INA") || !partesRegistro[6].equals("BLQ")
                    || !partesRegistro[6].equals("NRG")|| !partesRegistro[6].equals("EPN")){
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Estado recibido:" + partesRegistro[6].toString());
            
            }else{
                this.setEstado(partesRegistro[6]);
            }
        }
    }

    @Override
    public String format() {
        StringBuilder sb=new StringBuilder();
        sb.append(this.getCodigoSesion().toString());
        sb.append(Protocol.SEPARADOR);
        sb.append(this.getEstado());
        return sb.toString();
    }
}
