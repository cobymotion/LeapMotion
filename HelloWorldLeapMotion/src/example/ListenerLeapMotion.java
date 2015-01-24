
package example;

import com.leapmotion.leap.*;
/**
 * Clase de escucha para los movimientos
 * @author luis
 */

public class ListenerLeapMotion extends Listener {

    /**
     * Metodo que detecta cuando el leapmotion esta conectado
     * @param controller 
     */
    @Override
    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    /**
     * Metodo que detecta los eventos en el leapmotion 
     * @param controller 
     */
    @Override
    public void onFrame(Controller controller) {
        ///Obtenemos el fram que captura
        Frame frame = controller.frame();
        //habilitamos el gesto de los movimientos circulares
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        //configuracion para mejor deteccion 
        controller.config().setFloat("Gesture.Circle.MinRadius", 10.0f);
        controller.config().setFloat("Gesture.Circle.MinArc", .5f);
        controller.config().save();
        
        //sacamos una lista de los eventos que se presenten 
        GestureList lista = frame.gestures();
        //verificamos que haya pasado algo
        if (!lista.isEmpty()) {
            //recorremos entre los eventos que ocurrieron 
            for (Gesture gesture : lista) {                
                switch (gesture.type()) {
                    //Si el evento fue movimientos circulares
                    case TYPE_CIRCLE:                       
                        //instancia de un objeto para el movimiento
                        CircleGesture circle = new CircleGesture(gesture);
                        //a partir de un posicionamiento verificamos a que sentido esta el movimiento
                        if(circle.pointable().direction().angleTo(circle.normal())<= Math.PI/2)                            
                        {  //si se da en sentido del reloj (limite 255)
                            if(LeapMotion.intensidad<255)
                                    LeapMotion.intensidad++; //aumenta el contador
                        }
                        else {
                                if(LeapMotion.intensidad>0)
                                    LeapMotion.intensidad--; //disminuye el contador
                                }
                        System.out.println(LeapMotion.intensidad); //imprimo el contador
                        break;

                }
            }
        }
    }

}
