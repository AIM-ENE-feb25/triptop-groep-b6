package adapters;

import dictionaries.KramersDictionary;

public class KramersAdapter implements IDictionaryAdapter {
    private KramersDictionary dictionary = new KramersDictionary();
    private final String DICTIONARY_NAME = "Kramers";

    @Override
    public String translate(String word) {
        return dictionary.find(word);
    }

    @Override
    public String getName() {
        return DICTIONARY_NAME;
    }
}
