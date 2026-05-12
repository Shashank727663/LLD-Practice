package Design_Patterns.Strutctual;


public class Adapter {

    // Target Interface
    interface PaymentGateway {
        void pay(String orderId, int amount);
    }

    // Existing PayU implementation
    static class PayuGateway implements PaymentGateway {
        @Override
        public void pay(String orderId, int amt) {
            System.out.println("Payment done for : " + orderId + " is : " + amt);
        }
    }

    // Third-party Razorpay API
    static class RazorPayApi {
        public void makePayment(String orderId, int amt) {
            System.out.println(
                "Paid Rs. " + amt + " using Razorpay for invoice: " + orderId
            );
        }
    }

    // Adapter Class
    static class RazorpayAdapter implements PaymentGateway {

        private RazorPayApi razorpayAPI;

        public RazorpayAdapter() {
            this.razorpayAPI = new RazorPayApi();
        }

        @Override
        public void pay(String orderId, int amount) {
            razorpayAPI.makePayment(orderId, amount);
        }
    }

    // Client Class
    static class CheckoutService {

        private PaymentGateway paymentGateway;

        public CheckoutService(PaymentGateway paymentGateway) {
            this.paymentGateway = paymentGateway;
        }

        public void checkout(String orderId, int amount) {
            paymentGateway.pay(orderId, amount);
        }
    }

    // Main Class
    public static void main(String[] args) {

        CheckoutService checkoutService =
            new CheckoutService(new RazorpayAdapter());

        checkoutService.checkout("12", 1780);
    }
}