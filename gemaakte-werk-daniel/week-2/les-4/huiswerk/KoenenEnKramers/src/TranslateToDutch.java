
public class TranslateToDutch {
    public static void main(String[] args) throws Exception {
        Translator translator = new Translator();
        String word = "aeroplane";
        System.out.println(translator.translateWithDictionaries(word));
    }
}
