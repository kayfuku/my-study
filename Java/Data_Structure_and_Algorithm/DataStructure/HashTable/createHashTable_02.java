// Create a Hash Table.
// Author: Cracking the Coding Interview p.337 + kei.
// Date  : September 28, 2016


public void createHashTable(String[] strs) {
    HashMap<String, ArrayList<String>> map = new HashMap<>();

    for (String s : strs) {
        // Group strings so that they have a same anagram.
        String key = sortChars(s);

        // Add 's' to the group that it should belong to.
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<String>());
        }
        ArrayList<String> anagrams = map.get(key);
        anagrams.add(s);
    }
}


// Sort the letters in a string and 
// return the result string. 
public String sortChars(String s) {
    char[] chars = s.toCharArray(); 
    Arrays.sort(chars); 
    return new String(chars);
}






























