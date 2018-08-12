package almundo.empleado;

import java.util.Date;
import almundo.callcenter.EmpleadosDisponibles;

/**
 * Clase que representa un empleado del tipo director
 * 
 * @author Nelson Zapata
 *
 */
public class Director extends EmpleadoAbstract {

    /**
     * Constructor que recibe el repositorio de empleados
     * 
     * @param empleadosDisponibles Repositorio de empleados disponibles
     */
    public Director(EmpleadosDisponibles empleadosDisponibles) {
        super(empleadosDisponibles);
    }

    /**
     * Atiende el llamado que le es asignado
     */
    @Override
    public void run() {
        this.atenderLlamado();
    }

    /**
     * Se agrega como empleado disponible segun le corresponda
     */
    @Override
    public void agregarmeAEmpleadosDisponibles(EmpleadosDisponibles disponibilidad) {
        disponibilidad.agregarDirector(this);
    }

    @Override
    protected void hablar() {
        System.out.println("Hablando Director " + (new Date()).toString());
    }

    /**
     * Finaliza el llamado
     */
    @Override
    protected void finalizarLLamado() {
        System.out.println("Finalizando llamado Director " + (new Date()).toString());
    }

}
