package Design_Patterns.Creational;

import java.util.List;

public class builder {
    //"Builder pattern builds a complex object step by step. 
    // It separates the construction of a complex object from its representation, 
    // so that the same construction process can create different representations."
    //the Builder Pattern. It separates object construction from its representation, 
    // allowing us to build step-by-step while keeping the object immutable and readable.

    static class BurgerMeal { 
        // required
        private final String bunType; 
        private final String patty; 

        // optional 
        private final boolean hasCheese; 
        private final String Drink; 
        private final boolean hasFries; 

         private BurgerMeal(BurgerBuilder builder) {
        this.bunType = builder.bunType;
        this.patty = builder.patty;
        this.hasCheese = builder.hasCheese;
        this.Drink = builder.drink;
        this.hasFries = builder.hasFries;
    }

        // Static nested Builder class
    public static class BurgerBuilder {
        // Required
        private final String bunType;
        private final String patty;

        // Optional
        private boolean hasCheese;
        private List<String> toppings;
        private String side;
        private String drink;
        private boolean hasFries; 

        // Builder constructor with required fields
        public BurgerBuilder(String bunType, String patty) {
            this.bunType = bunType;
            this.patty = patty;
        }

        // Method to set cheese
        public BurgerBuilder withCheese(boolean hasCheese) {
            this.hasCheese = hasCheese;
            return this;
        }

        // Method to set toppings
        public BurgerBuilder withToppings(List<String> toppings) {
            this.toppings = toppings;
            return this;
        }

        // Method to set side
        public BurgerBuilder withSide(String side) {
            this.side = side;
            return this;
        }

        // Method to set drink
        public BurgerBuilder withDrink(String drink) {
            this.drink = drink;
            return this;
        }

        // Final build method
        public BurgerMeal build() {
            return new BurgerMeal(this);
        }
    
}
}
    public static void main(String[] args) {
        BurgerMeal burgerKing = new BurgerMeal.BurgerBuilder("Wheat", "Veg")
            .withCheese(true)
            .withDrink("Coke")
            .withSide("Fries")
            .build();
        
        }

}
