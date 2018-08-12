package almundo.callcenter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import almundo.empleado.Director;
import almundo.empleado.Empleado;
import almundo.empleado.Operador;
import almundo.empleado.Supervisor;

/**
 * Clase encargada del manejo de empleados
 * para atender llamados
 * 
 * @author Nelson Zapata
 *
 */
public class EmpleadosDisponibles {

    private Semaphore semaforoEmpleadoLibre;
    private Queue<Operador> operadores = new LinkedList<Operador>();
    private Queue<Supervisor> supervisores = new LinkedList<Supervisor>();
    private Queue<Director> directores = new LinkedList<Director>();

    /**
     * Constructor que recibe un semaforo para
     * soncronizar si hay empleados libres o no
     * 
     * @param semaforoEmpleadoLibre Semaforo para sincronizar empleados libres
     */
    public EmpleadosDisponibles(Semaphore semaforoEmpleadoLibre) {
        this.semaforoEmpleadoLibre = semaforoEmpleadoLibre;
    }

    /**
     * 
     * @return Devuelve un empleado segun un orden de prioridad
     */
    public Empleado getEmpleado() {
        Empleado empleadoInterface = null;

        if(!getOperadores().isEmpty()) {
            System.out.println("Se tomo OPERADOR");
            empleadoInterface = getOperadores().remove();
        } else if (!getSupervisores().isEmpty()) {
            System.out.println("Se tomo SUPERVISOR");
            empleadoInterface =  getSupervisores().remove();
        } else if (!getDirectores().isEmpty()) {
            System.out.println("Se tomo DIRECTOR");
            empleadoInterface = getDirectores().remove();
        }

        return empleadoInterface;
    }

    /**
     * Libera un empleado y avisa a traves del semaforo de empleados libres
     * 
     * @param empleado Empleado
     */
    public synchronized void liberarEmpleado(Empleado empleado) {
        empleado.agregarmeAEmpleadosDisponibles(this);
        semaforoEmpleadoLibre.release();
    }

    /**
     * Agrega un supervisor a la cola de supervisores
     * 
     * @param supervisor Supervisor
     */
    public void agregarSupervisor(Supervisor supervisor) {
        getSupervisores().add(supervisor); 
    }

    /**
     * Agrega un Director a la cola de directores
     * 
     * @param director Director
     */
    public void agregarDirector(Director director) {
        getDirectores().add(director); 
    }

    /**
     * Agrega operador a la cola de operadores
     * 
     * @param operador Operador
     */
    public void agregarOperador(Operador operador) {
        getOperadores().add(operador);    
    }

    /**
     * 
     * @return Devuelve la cola de operadores
     */
    public synchronized Queue<Operador> getOperadores() {
        return operadores;
    }

    /**
     * 
     * @return Devuelve la cola de supervisores
     */
    public synchronized Queue<Supervisor> getSupervisores() {
        return supervisores;
    }

    /**
     * 
     * @return Devuelve la cola de directores
     */
    public synchronized Queue<Director> getDirectores() {
        return directores;
    }

}
