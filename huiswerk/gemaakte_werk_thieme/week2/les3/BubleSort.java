package huiswerk.gemaakte_werk_thieme.week2.les3;

public class BubleSort implements ISorteerStrategie {
    @Override
    public void sorteer(String[] woorden) {
        for (int grens = woorden.length; grens > 0; grens--) {
            for (int i = 0; i < grens - 1; i++) {
                if (woorden[i].compareTo(woorden[i + 1]) > 0) {
                    String hulp = woorden[i];
                    woorden[i] = woorden[i + 1];
                    woorden[i + 1] = hulp;
                }
            }
        }
    }
}
