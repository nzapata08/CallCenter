package almundo.empleado;

import almundo.callcenter.CallCenter;
import almundo.callcenter.EmpleadosDisponibles;
import almundo.cliente.Llamado;

/**
 * Clase abstracta de comportamiento en comun entre empleados
 * 
 * @author Nelson Zapata
 *
 */
public abstract class EmpleadoAbstract implements Empleado {

    private EmpleadosDisponibles empleadosDisponibles;
    private Llamado llamado;

    /**
     * Constructor a implementar por las clases hijas
     * 
     * @param empleadosDisponibles Repositorio de empleados disponibles
     */
    public EmpleadoAbstract(EmpleadosDisponibles empleadosDisponibles) {
        this.empleadosDisponibles = empleadosDisponibles;
    }


    /**
     * Se le asigna el llamado al empleado y se inicia un nuevo
     * hilo para que lo atienda
     * 
     * @param llamado llamado a asignar al empleado
     */
    @Override
    public void asignarLlamado(Llamado llamado) {
        this.llamado = llamado;
        Thread thread = new Thread(this);
        thread.start();
    }


    /**
     * Atiende un llamado usando Template para atender el llamado 
     * 
     */
    public void atenderLlamado() {
        this.hablar();

        try {
            Thread.sleep(getLlamado().getTiempoDeLlamado() * 1000);
        } catch (InterruptedException e) {
            //Devuelve el llamado para que sea procesado nuevamente en caso de error
            CallCenter.getInstancia().dispatchCall(llamado);
        }

        this.finalizarLLamado();
        empleadosDisponibles.liberarEmpleado(this);
    }

    /**
     * Devuelve el llamado que le fue asignado
     * 
     * @return Devuelve el llamado que le fue asignado
     */
    protected Llamado getLlamado() {
        return llamado;
    }

    /**
     * Metodo abstracto que representa la comunicacion del empleado
     */
    protected abstract void hablar();

    /**
     * Finaliza un llamado
     */
    protected abstract void finalizarLLamado();
}
