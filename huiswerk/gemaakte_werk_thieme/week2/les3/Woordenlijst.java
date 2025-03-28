package huiswerk.gemaakte_werk_thieme.week2.les3;


public class Woordenlijst {
    private String[] woorden = {"hond", "beer", "leeuw", "kat", "aap", "tijger", "olifant", "olifant"};
    private ISorteerStrategie sorteerStrategie;

    public void setSorteerStrategie(ISorteerStrategie strategie) {
        this.sorteerStrategie = strategie;
    }

    public void sorteer() {
        if (sorteerStrategie == null) {
            throw new UnsupportedOperationException("Geen sorteeralgoritme geselecteerd");
        }
        sorteerStrategie.sorteer(woorden);
    }

    public void print() {
        for (String woord : woorden) {
            System.out.print(woord + " ");
        }
        System.out.println();
    }
}
