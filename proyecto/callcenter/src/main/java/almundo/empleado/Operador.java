package almundo.empleado;

import java.util.Date;

import almundo.callcenter.EmpleadosDisponibles;

/**
 * Clase que representa un empleado del tipo operador
 * 
 * @author Nelson Zapata
 *
 */
public class Operador extends EmpleadoAbstract {

    /**
     * Constructor que recibe el repositorio de empleados
     * 
     * @param empleadosDisponibles Repositorio de empleados disponibles
     */
    public Operador(EmpleadosDisponibles empleadosDisponibles) {
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
        disponibilidad.agregarOperador(this);
    }
    
    @Override
    protected void hablar() {
        System.out.println("Hablando Operador " + (new Date()).toString());
    }
    
    /**
     * Finaliza el llamado
     */
    @Override
    protected void finalizarLLamado() {
        System.out.println("Finalizando llamado Operador " + (new Date()).toString());
    }

}
