package almundo.cliente;

import almundo.callcenter.CallCenter;

/**
 * Clase que representa cliente que llama al call center
 * 
 * @author Nelson Zapata
 *
 */
public class Cliente extends Thread {

    private Llamado llamado ;

    /**
     * Llama al call center
     */
    public void llamarACallCenter() {
        llamado = new Llamado((int)(Math.random()*(10-5))+5);

        CallCenter.getInstancia().dispatchCall(llamado);
    }

    /**
     * Ejecuta el llamdo en un hilo separado
     */
    @Override
    public void run() {
        llamarACallCenter();
    }

    /**
     * Devuelve true si se recepciono llamado
     * 
     * @return
     */
    public boolean seRecepcionoLlamado() {

        if(this.llamado != null) {
            return this.llamado.isSeRecepcionoLlamado();
        }

        return false;
    }

}
