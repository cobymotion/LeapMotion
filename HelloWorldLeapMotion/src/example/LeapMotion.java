package example;

import com.leapmotion.leap.*;
import java.io.IOException;

/**
 *
 * @author luis
 */
public class LeapMotion {

    //variable estatica que almacena el valor segun los movimientos
    public static int intensidad = 0;

    public static void main(String... a) {
        //instanciamos de nuestra clase de escucha 
        ListenerLeapMotion listener = new ListenerLeapMotion();
        //instanciamos la clase para control de los datos 
        Controller controller = new Controller();
        //añadimos el escucha para que tome los eventos 
        controller.addListener(listener);        
        System.out.println("Presiona algo para salir");
        try {
            //detemos el programa hasta que presionemos algo
            System.in.read();
        } catch (IOException e) {
            System.out.println("Ocurrio un error");
        }

        //Se remueve el listener
        controller.removeListener(listener);

    }

}
