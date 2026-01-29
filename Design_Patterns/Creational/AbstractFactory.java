package Design_Patterns.Creational;
//The Abstract Factory Pattern is a creational design pattern 
// that provides an interface for creating families of related or 
// dependent objects without specifying their concrete classes.


// when to use? 
// You use it when you have multiple factories, each responsible 
// for producing objects that are meant to work together.


public class AbstractFactory {
public interface PaymentGateway {
    void ProcessPayment(double amount); 
}

public interface Invoice {
    void GenerateInvoice(); 
}


// Indian payment 
static class RazorPay implements PaymentGateway { 
   public void ProcessPayment (double amount) { 
        System.out.println("Payment For" + amount + " Is done through RazorPay");
    }
}

static class PayuGateway implements PaymentGateway { 
     public void ProcessPayment (double amount) { 
        System.out.println("Payment For" + amount + " Is done through Payu");
    }
}


// US Payment 
static class StripeGateWay implements PaymentGateway { 
     public void ProcessPayment (double amount) { 
        System.out.println("Payment For" + amount + " Is done through Stripe");
    }
}

static class PayPal implements PaymentGateway { 
     public void ProcessPayment (double amount) { 
        System.out.println("Payment For" + amount + " Is done through PayPal");
    }
}

// Invoice Generators 
static class USInvoice implements Invoice {
    public void GenerateInvoice() {
        System.out.println("Generating Invoice as per US norms.");
    }
}

static class GSTInvoice implements Invoice {
    public void GenerateInvoice() {
        System.out.println("Generating GST Invoice for India.");
    }
}

interface RegionFactory {
    PaymentGateway createPaymentGateway(String gatewayType);
    Invoice createInvoice();
}


// ========== Concrete Factories ==========
static class IndiaFactory implements RegionFactory {
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if (gatewayType.equalsIgnoreCase("razorpay")) {
            return new RazorPay();
        } else if (gatewayType.equalsIgnoreCase("payu")) {
            return new PayuGateway();
        }
        throw new IllegalArgumentException("Unsupported gateway for India: " + gatewayType);
    }

    public Invoice createInvoice() {
        return new GSTInvoice();
    }
}

static class USFactory implements RegionFactory {
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if (gatewayType.equalsIgnoreCase("paypal")) {
            return new PayPal();
        } else if (gatewayType.equalsIgnoreCase("stripe")) {
            return new StripeGateWay();
        }
        throw new IllegalArgumentException("Unsupported gateway for US: " + gatewayType);
    }

    public Invoice createInvoice() {
        return new USInvoice();
    }
}

static class CheckoutService {
    private PaymentGateway paymentGateway;
    private Invoice invoice;
    private String gatewayType;

    public CheckoutService(RegionFactory factory, String gatewayType) {
        this.gatewayType = gatewayType;
        this.paymentGateway = factory.createPaymentGateway(gatewayType);
        this.invoice = factory.createInvoice();
    }

    public void completeOrder(double amount) {
        paymentGateway.ProcessPayment(amount);
        invoice.GenerateInvoice();
    }
}

    public static void main(String[] args) {
        CheckoutService indiaCheckout = new CheckoutService(new IndiaFactory(), "razorpay");
        indiaCheckout.completeOrder(1999.0);

        System.out.println("---");

        // Using PayPal in US
        CheckoutService usCheckout = new CheckoutService(new USFactory(), "paypal");
        usCheckout.completeOrder(49.99);
    
    }
    
}
