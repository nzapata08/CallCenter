package almundo.empleado;

import almundo.callcenter.EmpleadosDisponibles;
import almundo.cliente.Llamado;

/**
 * Contrato que debe implementar un empleado que atiende llamadas
 * 
 * @author Nelson Zapata
 *
 */
public interface Empleado extends Runnable {

    /**
     * Asigna el llamado a un empleado
     * 
     * @param llamado Llamado a asignar al empleado
     */
    void asignarLlamado(Llamado llamado);
    
    /**
     * Se devuelve como empleado disponible segun le corresponda
     * 
     * @param empleadosDisponibles Repositorio de empleados disponibles
     */
    void agregarmeAEmpleadosDisponibles(EmpleadosDisponibles empleadosDisponibles);

}
