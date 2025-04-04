package adapters;

import dictionaries.KoenenDictionary;

public class KoenenAdapter implements IDictionaryAdapter {
    private KoenenDictionary dictionary = new KoenenDictionary();
    private final String DICTIONARY_NAME = "Koenen";

    @Override
    public String translate(String word) {
        return dictionary.lookUp(word);
    }

    @Override
    public String getName() {
        return DICTIONARY_NAME;
    }
}
