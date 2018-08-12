package almundo.callcenter;

import almundo.cliente.Llamado;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestCallCenter extends TestCase {

    public void testCallCenter() {
        try {
            CallCenter dispatchCallCenter = CallCenter.getInstancia();

            for (int i = 1; i <= 11; i++) {
                int duracionLlamad = (int)(Math.random()*(10-5))+5;
                Llamado llamado = new Llamado(5);
                dispatchCallCenter.dispatchCall(llamado);
            }

            //Sleep para mostrar el procesamiento de los llamados realizados
            Thread.sleep(10000);

            dispatchCallCenter.setRecepcionarLlamados(false);

            assertTrue(true);

        } catch (Exception e) { 
            Assert.fail( e.getMessage());
        }

    }


}
