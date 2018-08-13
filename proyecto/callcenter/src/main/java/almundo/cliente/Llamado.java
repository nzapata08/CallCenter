package almundo.cliente;

/**
 * Clase que representa un llamado para un empleado
 * 
 * @author Nelson Zapata
 *
 */
public class Llamado {

    private int tiempoDeLlamado;
    private boolean seRecepcionoLlamado;

    /**
     * Constructo que recibe el tiempo que va a
     * durar la llamada
     * 
     * @param tiempoDeLlamado Tiempo de duracion de llamada
     */ 
    public Llamado(int tiempoDeLlamado) {
        this.tiempoDeLlamado = tiempoDeLlamado;
    }

    /**
     * 
     * @return Devuelve el tiempo de duracion de la llamada
     */
    public int getTiempoDeLlamado() {
        return tiempoDeLlamado;
    }

    /**
     * Setea el tiempo de llamado
     * 
     * @param tiempoDeLlamado Tiempo de llamado
     */
    public void setTiempoDeLlamado(int tiempoDeLlamado) {
        this.tiempoDeLlamado = tiempoDeLlamado;
    }

    /**
     * Devuelve true si se se recepcion llamado
     * 
     * @return Devuelve true si se se recepcion llamado
     */
    public boolean isSeRecepcionoLlamado() {
        return seRecepcionoLlamado;
    }

    /**
     * Setea si se recepciono la llamada
     * 
     * @param seRecepcionoLlamado ¿Se recepciono la llamada?
     */
    public void setSeRecepcionoLlamado(boolean seRecepcionoLlamado) {
        this.seRecepcionoLlamado = seRecepcionoLlamado;
    }
}
