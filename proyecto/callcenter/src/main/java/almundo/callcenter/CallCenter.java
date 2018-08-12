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
public class CallCenter implements Runnable {

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
    public CallCenter(Semaphore semaforollamadoPendiente, Semaphore semaforoEmpleadoLibre, EmpleadosDisponibles empleadosDisponibles) {
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
            while (DispatchCallCenter.getInstancia().isRecepcionarLlamados()) {
                semaforollamadoPendiente.acquire();
                Llamado llamadoActual = DispatchCallCenter.getInstancia().getLlamado();

                semaforoEmpleadoLibre.acquire();
                Empleado empleado = empleadosDisponibles.getEmpleado();
                empleado.asignarLlamado(llamadoActual);
            }

        } catch (InterruptedException e) {
            DispatchCallCenter.getInstancia().setHuboErrorEnCallCenter(true);
        }

    }
}
