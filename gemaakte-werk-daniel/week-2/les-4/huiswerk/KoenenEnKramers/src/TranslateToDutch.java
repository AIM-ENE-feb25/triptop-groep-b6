
public class TranslateToDutch {
    public static void main(String[] args) throws Exception {
        Translator translator = new Translator();
        //kramers
        System.out.println(translator.translateWithDictionaries("aeroplane"));

        //koenen
        System.out.println(translator.translateWithDictionaries("submarine"));

        //bestaat niet
        System.out.println(translator.translateWithDictionaries("asdf"));
    }
}
