package Design_Patterns.Creational;
import java.util.*;

public class Factory {
    public interface InnerFactory {
        void draw(); 
    }

    static class Circle implements InnerFactory { 
        @Override
        public void draw() { 
            System.out.println("Drawing Circle");
        }
    }


    static class Rectage implements InnerFactory { 
        @Override
        public void draw() { 
            System.out.println("Drawing Rectangle");
        }
    }
    // definition for Factory PAtterrn 
    // Rather than calling a constructor directly 
    // to create an object, 
    // we use a factory method to create that object based on some input or condition.

   static class ShapeFactory { 
        public InnerFactory getShape (String curve) { 
            if(curve.equalsIgnoreCase("Circle")) return new Circle(); 

            else if(curve.equalsIgnoreCase("Rectangle")) return new Rectage(); 

            else return null; 
        }
    }
    public static void main(String[] args) {
        // Object of ShapeFactory is initialized
        ShapeFactory shapeFactory = new ShapeFactory();

        // Get a Circle object and call its draw method
        InnerFactory shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        // Get a  object and call its draw method
        InnerFactory shape2 = shapeFactory.getShape("Rectangle");
        shape2.draw();
    }
}
