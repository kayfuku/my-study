// Convert a Hash Table to an array.
// Author: Cracking the Coding Interview 11.2 p.337 + kei.
// Date  : February 13, 2016


public void convertHashTableToArray(String[] strs) {
    // Create a Hash Table.
    HashMap<String, ArrayList<String>> map = new HashMap<>();

    for (String s : strs) {
        // Group strings 'strs' so that they have a same anagram.
        String key = sortChars(s);

        // Add 's' to the group that it should belong to.
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<String>());
        }
        ArrayList<String> anagrams = map.get(key);
        anagrams.add(s);
    }

    // Convert a Hash Table to an array.
    int index = 0;
    for (String key : map.keySet()) {
        ArrayList<String> list = map.get(key);
        for (String word : list) {
            strs[index] = word;
            index++;
        }
    }
}

// Sort the letters in a string and 
// return the result string. 
public String sortChars(String s) {
    char[] chars = s.toCharArray(); 
    Arrays.sort(chars); 
    return new String(chars);
}
































