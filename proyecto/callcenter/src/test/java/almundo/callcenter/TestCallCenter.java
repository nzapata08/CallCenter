package almundo.callcenter;

import almundo.cliente.Llamado;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestCallCenter extends TestCase {

    public void testCallCenter() {
        try {
            DispatchCallCenter dispatchCallCenter = DispatchCallCenter.getInstancia();

            for (int i = 1; i <= 11; i++) {
                Math.random();

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
