package huiswerk.gemaakte_werk_thieme.week2.les3;

public class Main {
    public static void main(String[] args) {
        Woordenlijst lijst = new Woordenlijst();

        System.out.println("Oorspronkelijke lijst:");
        lijst.print();

        // BubbleSort gebruiken
        lijst.setSorteerStrategie(new BubleSort());
        lijst.sorteer();
        System.out.println("Gesorteerd met BubbleSort:");
        lijst.print();

        // Nieuwe niet-gesorteerde lijst maken
        lijst = new Woordenlijst();
        lijst.setSorteerStrategie(new SelectSort());
        lijst.sorteer();
        System.out.println("Gesorteerd met SelectionSort:");
        lijst.print();
    }
}
