# Voorbereiding les 2

## Aangeweze design-principe:

Composition over inheritance

## Te beantwoorden vragen:

1. De naam en definitie van het principe.
2. De consequenties van het toepassen van het principe.
3. Een code voorbeeld waarin het principe wordt toegepast.
4. Op welk design property of properties het principe gebaseerd is.

## Antwoorden:

### 1. De naam en definitie van het principe.

Composition over inheritance is een design principe dat aangeeft dat code herbruikt moet worden door het te componeren
ipv te inheriten. Dit betekent dat je classes niet moet extenden om de code te kunnen hergebruiken, maar dat je classes
moet componeren met andere classes. Dit zorgt ervoor dat je code flexibeler en minder complex wordt.

### 2. De consequenties van het toepassen van het principe.

De consequenties van het toepassen van CoI zijn dat je code flexibeler en minder complex wordt. Dit komt doordat je
classes niet meer vast zitten aan een bepaalde implementatie van een andere class. Hierdoor kan je classes makkelijker
hergebruiken en aanpassen. Maar het kan ook leiden tot meer boilerplate code, omdat je classes nu meer code nodig
hebben.

Het kan ook moeilijker te begrijpen zijn als je er naar kijkt voor het eerst. Dit komt doordat je nu meerdere classes
moet bekijken om te begrijpen hoe een class werkt.

### 3. Een code voorbeeld waarin het principe wordt toegepast.

```java

public class Engine {
    public void start() {
        System.out.println("Engine started");
    }
}

public class Car {
    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        engine.start();
        System.out.println("Car started");
    }
}

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();
        Car car = new Car(engine);
        car.start();
    }
}
```

### 4. Op welk design property of properties het principe gebaseerd is.
CoI is gebasseerd op Coupling.