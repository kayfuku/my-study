// Write a method to return all subsets of a set. 
// ex.  (2, 4, 7)
//  0    0  0  0  -> ()
//  1    0  0  1  -> (7)
//  2    0  1  0  -> (4)
//  3    0  1  1  -> (4, 7)
//  4    1  0  0  -> (2)
//  5    1  0  1  -> (2, 7)
//  6    1  1  0  -> (2, 4)
//  7    1  1  1  -> (2, 4, 7)
//
// Author: CtCI 6th p.349 + kei
// Date  : October 7, 2016


ArrayList<ArrayList<Integer>> getAllSubsets(ArrayList<Integer> set) {
	ArrayList<ArrayList<Integer>> allSubsets = new ArrayList<ArrayList<Integer>>();
	// Compute 2^n. max: 1 0 0 0
	int max = 1 << set.size();
	for (int k = 0; k < max; k++) {
		ArrayList<Integer> subset = convertIntToSet(k, set);
		allSubsets.add(subset);
	}

	return allSubsets;
}

ArrayList<Integer> convertIntToSet(int x, ArrayList<Integer> set) {
	ArrayList<Integer> subset = new ArrayList<Integer>();
	int index = 0;
	// Get and remove the LSB of k, like pop().
	// '>>=' is to do arithmatic right shift
	// and assign it to k.
	for (int k = x; k > 0; k >>= 1) {
		// (k & 1) is to get the LSB of k.
		if ((k & 1) == 1) {
			subset.add(set.get(index));
		}
		index++;
	}
	return subset;
}




























