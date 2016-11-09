// Array to Hash Table.
// Author: Cracking the Coding Interview
// Date  : January 31, 2016


class HashTable {
    public HashMap<Integer, Student> buildMap(Student[] students) {
        HashMap<Integer, Student> map = new HashMap<Integer, Student>();
        for (Student s : students) {
            map.put(s.getId(), s);        
        }

        return map;
    }
}
