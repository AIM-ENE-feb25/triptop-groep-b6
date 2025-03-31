public class IDealPaymentSystem implements PaymentSystemInterface {
    @Override
    public void pay(double amount) {
        System.out.println("Ik betaalde " + amount + " met iDeal");
    }

}
