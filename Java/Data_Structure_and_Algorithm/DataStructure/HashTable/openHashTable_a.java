// Hash Table. (Open Hash, Chain Hash)
// It uses array as a hashtable and uses linkedlist for collision.
// Insertion: O(1)
// Retrieval: O(1)
// Deletion : O(1)
// Worst Case  : O(N) (Too many collision)
// Author: アルゴリズムを学ぼう p.141 + kei
// Date  : September 29, 2016


public class OpenHashTable<K, V> extends AbstractHashTable<K, V> {
    private int size;
    private LinkedList<Entry<K, V>>[] table;

    public OpenHashTable() {
        size = 0;
        table = new LinkedList[16];
    }

    public int size() {
        return size;
    }

    @Override
    public void insert(K key, V value) {
        if (key == null) {
            throw new RuntimeException("Key should not be empty.");
        }

        // Compute index.
        int index = key.hashCode() % table.length;
        insertTable(table, index, key, value);

        if (size() >= table.length) {
            reconstruct();
        }
    }

    private void insertTable(LinkedList<Entry<K, V>>[] table, 
                             int index, K key, V value) {
        // Nothing is inserted yet.
        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
        }

        // Find key in the table.
        for (Iterator<Entry<K, V>> it = table[index].iterator(); 
             it.hasNext(); ) {
            Entry<K, V> entry = it.next();
            if (key.equals(entry.key)) {
                entry.value = value;
                return;
            }
        }

        // Not found.
        table[index].add(new Entry<K, V>(key, value));

        ++size;
    }

    @Override
    public V find(K key) {
        // Compute index.
        int index = key.hashCode() % table.length;

        // Nothing is inserted yet.
        if (table[index] == null) {
            return null;
        }

        // Find key in the table.
        for (Iterator<Entry<K, V>> it = table[index].iterator(); 
             it.hasNext(); ) {
            Entry<K, V> entry = it.next();
            if (key.equals(entry.key)) {
                return entry.value;
            }
        }

        // Not found.
        return null;
    }

    @Override
    public void remove(K key) {
        // Compute index.
        int index = key.hashCode() % table.length;

        // Nothing is inserted yet.
        if (table[index] == null) {
            return;
        }

        // Find key in the table.
        for (Iterator<Entry<K, V>> it = table[index].iterator(); 
             it.hasNext(); ) {
            Entry<K, V> entry = it.next();
            if (key.equals(entry.key)) {
                it.remove();
                --size;
                return;
            }
        }
    }

    private void reconstruct() {
        int newSize = table.length * 2;
        // Create new table whose size is bigger than original.
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[newSize];
        this.size = 0;

        // Copy the original data to new table.
        for (int i = 0; i < table.length; ++i) {
            if (table[i] == null) {
                continue;
            }

            for (Iterator<Entry<K, V>> it = table[i].iterator();
                 it.hasNext(); ) {
                Entry<K, V> entry = it.next();
                int index = entry.key.hashCode() % newSize;
                insertTable(newTable, index, entry.key, entry.value);
            }
        }

        this.table = newTable;
    }

}


abstract class AbstractHashTable<K, V> {

    static class Entry<K, V> {
        public K key;
        public V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public abstract int size();
    public abstract void insert(K key, V value);
    public abstract V find(K key);
    public abstract void remove(K key);
}


























