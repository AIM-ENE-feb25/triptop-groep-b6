package factories;

import adapters.IDictionaryAdapter;
import adapters.KoenenAdapter;
import adapters.KramersAdapter;

public class DictionaryFactoryAdapter {
    public IDictionaryAdapter getDictionary(String dictionaryName) {
        switch (dictionaryName) {
            case "Koenen":
                return new KoenenAdapter();
            case "Kramers":
                return new KramersAdapter();
            default:
                return null;
        }
    }
}
