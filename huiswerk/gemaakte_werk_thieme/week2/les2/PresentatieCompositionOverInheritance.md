

## De naam en definitie van Composition over Inheritance

Composition over inheritance is een design principe dat aangeeft dat code herbruikt moet worden door het te componeren
ipv te inheriten. Dit betekent dat je classes niet moet extenden om de code te kunnen hergebruiken, maar dat je classes
moet componeren met andere classes. Dit zorgt ervoor dat je code flexibeler en minder complex wordt.

## De consequenties van het toepassen van Composition over Inheritance
Consequenties van het toepassen van het principe, oftewel composition gebruiken i.p.v inheritance. Het gebruik van composition zorgt ervoor dat de code 
* flexibeler is en minder complex wordt. 
* Makkelijker te onderhouden, omdat klassen minder impact hebben op elkaar. 
* Het enige nadeel is dat het er kan wel voor kan zorgen dat je meer code nodig hebt.
* Lastiger te lezen als je voor het eerst naar deze code kijkt.

## Een code voorbeeld waarin Composition over Inheritance wordt toegepast
Zie hieronder.
### Code voorbeeld goede variant
```java
package prototype;

public class OnlineShopCompositie {
    // Stap 1: Maak een interface voor alle betaalmethoden
    interface PaymentMethod {
        void pay(double amount);
    }

    // Stap 2: Maak concrete implementaties
    class CreditCardPayment implements PaymentMethod {
        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Credit Card.");
        }
    }

    class PayPalPayment implements PaymentMethod {
        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using PayPal.");
        }
    }

    class BitcoinPayment implements PaymentMethod {
        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Bitcoin.");
        }
    }

    // Stap 3: Compositie in OnlineShop
    class OnlineShop {
        private PaymentMethod paymentMethod;

        public OnlineShop(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod; // Injecteer de betalingsmethode
        }

        public void checkout(double amount) {
            paymentMethod.pay(amount);
        }
    }

    // Stap 4: Gebruik
    public class Main {
        public static void main(String[] args) {
            // CreditCard betaling
            OnlineShop shop1 = new OnlineShop(new CreditCardPayment());
            shop1.checkout(100);

            // PayPal betaling
            OnlineShop shop2 = new OnlineShop(new PayPalPayment());
            shop2.checkout(50);

            // Bitcoin betaling
            OnlineShop shop3 = new OnlineShop(new BitcoinPayment());
            shop3.checkout(75);
        }
    }
}
```

### Code voorbeeld foute variant

````java

package prototype;

public class OnlineShopInheritance {

    class OnlineShop {
        void processPayment(double amount) {
            System.out.println("Processing payment of $" + amount + " via default method");
        }
    }

    class CreditCardShop extends OnlineShop {
        @Override
        void processPayment(double amount) {
            System.out.println("Processing payment of $" + amount + " via Credit Card");
        }
    }

    class PayPalShop extends OnlineShop {
        @Override
        void processPayment(double amount) {
            System.out.println("Processing payment of $" + amount + " via PayPal");
        }
    }

    public class Main {
        public static void main(String[] args) {
            OnlineShop shop = new CreditCardShop();
            shop.processPayment(100);
        }
    }
}
 

````

## Op welk design property of properties het principe gebaseerd is
Composition over Inheritance is gebaseerd op het design property van flexibiliteit & Coupling


