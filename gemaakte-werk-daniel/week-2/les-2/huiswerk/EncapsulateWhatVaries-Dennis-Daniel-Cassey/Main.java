public class Main {
    public static void main(String[] args) {
        PaymentSystemInterface system;
        double amount = 1.99;

//        iDeal
        system = new IDealPaymentSystem();
        system.pay(amount);

//        PayPal
        system = new PayPalPaymentSystem();
        system.pay(amount);
    }
}