package almundo.callcenter;

import java.util.concurrent.Semaphore;

import almundo.cliente.Llamado;
import almundo.empleado.Empleado;

/**
 * Clase encargada de consumir llamados y asignarselos
 * a un empleado que se encuentre disponible
 * 
 * @author Nelson Zapata
 *
 */
public class Dispatch implements Runnable {

    private EmpleadosDisponibles empleadosDisponibles;
    private Semaphore semaforollamadoPendiente;
    private Semaphore semaforoEmpleadoLibre;

    /**
     * Constructor con parametros
     * 
     * @param semaforollamadoPendiente Semaforo para notificacion de llamados pendientes
     * @param semaforoEmpleadoLibre Semaforo que determina si hay empleado libre para atender llamado
     * @param empleadosDisponibles Repositorio de empleados que atienden llamados
     */
    public Dispatch(Semaphore semaforollamadoPendiente, Semaphore semaforoEmpleadoLibre, EmpleadosDisponibles empleadosDisponibles) {
        this.semaforollamadoPendiente = semaforollamadoPendiente;
        this.empleadosDisponibles = empleadosDisponibles;
        this.semaforoEmpleadoLibre = semaforoEmpleadoLibre;
    }

    /**
     * Metodo que consume llamadas pendientes
     * y se las asigna a un empleado para que las atienda
     */
    @Override
    public void run() {
        try {
            while (CallCenter.getInstancia().isRecepcionarLlamados()) {
                dispatchCall();
            }
        } catch (InterruptedException e) {
            CallCenter.getInstancia().setHuboErrorEnCallCenter(true);
        }
    }

    /**
     * Asigna a un empleado una llamada
     * 
     * @throws InterruptedException Error al adquirir recurso
     */
    public void dispatchCall() throws InterruptedException {
        semaforollamadoPendiente.acquire();
        Llamado llamadoActual = CallCenter.getInstancia().getLlamado();

        semaforoEmpleadoLibre.acquire();
        Empleado empleado = empleadosDisponibles.getEmpleado();
        empleado.asignarLlamado(llamadoActual);
    }
}
