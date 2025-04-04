import adapters.IDictionaryAdapter;
import factories.DictionaryFactoryAdapter;

public class Translator {
    DictionaryFactoryAdapter dictionaryFactoryAdapter = new DictionaryFactoryAdapter();
    IDictionaryAdapter dictionaryAdapter;

    private final String KOENEN_DICTIONARY_NAME = "Koenen";
    private final String KRAMERS_DICTIONARY_NAME = "Kramers";

    public String translateWord(IDictionaryAdapter dictionary, String word) {
        return dictionary.translate(word);
    }

    public String translateWithDictionaries(String word) {
        String result;
        dictionaryAdapter = dictionaryFactoryAdapter.getDictionary(KOENEN_DICTIONARY_NAME);
        result = translateWord(dictionaryAdapter, word);
        if (result == null) {
            dictionaryAdapter = dictionaryFactoryAdapter.getDictionary(KRAMERS_DICTIONARY_NAME);
            result = translateWord(dictionaryAdapter, word);
            if (result == null) {
                return "no translation found";
            }
        }
        return result;
    }
}
