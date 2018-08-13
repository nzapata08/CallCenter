package almundo.callcenter;

import java.util.ArrayList;
import java.util.List;

import almundo.cliente.Cliente;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestCallCenter extends TestCase {

    /**
     * Se procedio a realizar una clase CallCenter que se encargue de recibir todos los llamados
     * y los encola para que despues un hilo independiente Dispatch los vaya asignando a los empleados
     * disponibles, por cada empleado disponible se genera un hilo para que atienda los llamados
     * concurrentemente con otros, cuando se libera el empleado este se vuelve a agregar a la cola
     * de empleados disponibles y a traves de un semaforo sincroniza con el dispatch para que este
     * sepa que hay un empleado disponible en caso de llamado, En caso de no haber empleados disponibles
     * las llamadas quedan encoladas y el dispatcher queda a la espera de algun empleado
     * que las pueda procesar y en caso de no haber llamadas pendientes el dispatcher queda a la espera
     * de una llamada bloqueandose con un semaforo, cuando llega un llamado ese semaforo es incrementado
     * para que el dispatcher sepa que hay una llamada pendiente
     * 
     */
    /**
     * 
     */
    public void testCallCenter() {
        try {
            int cantLlamadosEmitidos = 11;

            CallCenter.getInstancia();

            List<Cliente> clientes = new ArrayList<Cliente>();
            for (int i = 1; i <= cantLlamadosEmitidos; i++) {
                Cliente cliente = new Cliente();
                clientes.add(cliente);
                cliente.start();
            }

            //Sleep para mosrar el procesamiento de llamados
            Thread.sleep(25000);

            assertTrue(true);

        } catch (Exception e) { 
            Assert.fail( e.getMessage());
        }
    }
}
