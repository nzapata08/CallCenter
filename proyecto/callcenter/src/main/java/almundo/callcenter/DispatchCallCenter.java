package almundo.callcenter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import almundo.cliente.Llamado;
import almundo.empleado.Director;
import almundo.empleado.Operador;
import almundo.empleado.Supervisor;

/**
 * Clase encargada de inicializar 
 * 
 * 
 * @author Nelson Zapata
 *
 */
public class DispatchCallCenter {

    private int cantidadOperadores = 7;
    private int cantidadSupervisores = 3;
    private int cantidadDirectorores = 1;
    private  Queue<Llamado> llamadosPendientes = new LinkedList<Llamado>();
    private  Semaphore semaforoLlamadoPendiente = new Semaphore(0);
    private Semaphore semaforoEmpleadoLibre = new Semaphore(cantidadOperadores + cantidadSupervisores + cantidadDirectorores);
    private EmpleadosDisponibles empleadosDisponibles;
    private boolean huboErrorEnCallCenter = false;

    private static DispatchCallCenter dispatchCallCenter;
    private boolean recepcionarLlamados = true;

    public static DispatchCallCenter getInstancia() {
        if(dispatchCallCenter == null) {
            dispatchCallCenter = new DispatchCallCenter();
        }
        return dispatchCallCenter;
    }

    /**
     * Constructor sin parametros
     */
    private DispatchCallCenter () {
        inicializar();
    }

    public void inicializar() {
        empleadosDisponibles = new EmpleadosDisponibles(semaforoEmpleadoLibre);
        iniciarEmpleados(empleadosDisponibles);
        Thread dispatcher = new Thread(new CallCenter(semaforoLlamadoPendiente, semaforoEmpleadoLibre, empleadosDisponibles));
        dispatcher.start();
    }

    /**
     * Atiende un llamado y lo agrega a una cola para ser procesado
     * 
     * @param llamado
     */
    public void dispatchCall(Llamado llamado) {
        llamadosPendientes.add(llamado);
        semaforoLlamadoPendiente.release();

        // En caso de haber error se vuelve a lanzar hilo gestor de llamadas
        if(this.huboErrorEnCallCenter) {
            Thread dispatcher = new Thread(new CallCenter(semaforoLlamadoPendiente, semaforoEmpleadoLibre, empleadosDisponibles));
            dispatcher.start();
        }
    }   

    /**
     * 
     * @return Devuelve un llamado pendiente
     */
    public Llamado getLlamado() {
        return llamadosPendientes.element();
    }

    /**
     * 
     * @return Devuelve true si hay que parar de recibir llamados, false en caso contrario
     */
    public boolean isRecepcionarLlamados() {
        return recepcionarLlamados;
    }

    /**
     * Setea si hay que recepcionar llamados o no
     * 
     * @param recepcionarLlamados ¿Recepcionar llamados?
     */
    public void setRecepcionarLlamados(boolean recepcionarLlamados) {
        this.recepcionarLlamados = recepcionarLlamados;
    }

    /**
     * Inicializa los empleados segun parametros iniciales
     * 
     * @param empleadosDisponibles Repositorio de empleados
     */
    private void iniciarEmpleados(EmpleadosDisponibles empleadosDisponibles) {
        for (int i = 1; i <= cantidadOperadores; i++){
            empleadosDisponibles.agregarOperador(new Operador(empleadosDisponibles));
        }

        for (int i = 1; i <= cantidadSupervisores; i++){
            empleadosDisponibles.agregarSupervisor(new Supervisor(empleadosDisponibles));
        }

        for (int i = 1; i <= cantidadDirectorores; i++){
            empleadosDisponibles.agregarDirector(new Director(empleadosDisponibles));
        }
    }

    /**
     * Devuelve true si hubo error en el call center
     * 
     * Devuelve true si hubo error en el call center
     */
    public boolean isHuboErrorEnCallCenter() {
        return huboErrorEnCallCenter;
    }

    /**
     * Setea si hubo error en el call center
     * 
     * @param huboErrorEnCallCenter ¿ Hubo error en el call center?
     */
    public void setHuboErrorEnCallCenter(boolean huboErrorEnCallCenter) {
        this.huboErrorEnCallCenter = huboErrorEnCallCenter;
    }
}