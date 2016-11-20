// Sort Library fundamentals.


// Sort objects in an array using Comparator. 
// Author: PIE p.76 + kei.
// Date  : November 1, 2016
Node[] nodesArray = new Node[10];

Arrays.sort(nodesArray, new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            int n1 = node1.data, n2 = node2.data;
            return (n1 < n2 ? -1 : (n1 == n2 ? 0 : 1));
        }
    });


// Basics. 
// Author: EPI p.216 + internet + kei.
// Date  : November 1, 2016

// Sort an array in increasing order. 
// O(N log N) time, O(N) (O(N/2)) space for randomly ordered inputs. 
// O(N) time, O(1) space for nearly sorted inputs. 
Arrays.sort(array);  
// Decreasing order.                                  
Arrays.sort(array, Comparator.reverseOrder());   
// Use custom Comparator.      
Arrays.sort(array, (x,y)->Integer.compare(y, x));   
// Sort a list in increasing order. O(N log N) time, O(N) space. 
Collections.sort(list);                               
Collections.sort(list, Collections.reverseOrder());   
// Decreasing order.                                  
Collections.sort(list, (x,y)->Integer.compare(y, x)); 
list.sort(Collections.reverseOrder());            




// Author: EPI p.216 + kei.
// Date  : November 16, 2016
public static class Student implements Comparable<Student> {
    public String name;
    public double gradePointAverage;

    Student(String name, double gradePointAverage) {
        this.name = name;
        this.gradePointAverage = gradePointAverage;
    }

    @Override
    public int compareTo(Student that) {
        return name.compareTo(that.name);
    }
}


public static void sortByName(List<Student> students) {
    Collections.sort(students);
}

public static void sortByGPA(List<Student> students) {
    Collections.sort(students, Collections.reverseOrder(new Comparator<Student>() {
        @Override
        public int compare(Student a, Student b) {
            return Double.compare(a.gradePointAverage, b.gradePointAverage);

        }
    }));
}










































