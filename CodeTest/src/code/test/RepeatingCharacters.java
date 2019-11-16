package code.test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author krishnamanchikalapudi
 *
 *         PROBLEM: find the length of the longest substring without repeating
 *         characters.
 */
public class RepeatingCharacters {

	private int lengthOfLongestSubstring(String input) {
		int ans = 0;
		for (int i = 0; i < input.length(); i++)
			for (int j = i + 1; j <= input.length(); j++) {
				if (isUnique(input, i, j)) {
					ans = Math.max(ans, j - i);
				} // if (allUnique(input, i, j))
			} // for (int j = i + 1; j <= input.length(); j++)
		return ans;
	}

	private boolean isUnique(String input, int start, int end) {
		Set<Character> hset = new HashSet<>();
		for (int i = start; i < end; i++) {
			Character ch = input.charAt(i);
			if (hset.contains(ch)) {
				return false;
			} // if (set.contains(ch))
			hset.add(ch);
		} // for (int i = start; i < end; i++)
		System.out.println(hset.toString());
		return true;
	}

	public static void main(String[] args) {
		String[] problems = new String[] { "abcabecbb", "aabcdeeab" };
		RepeatingCharacters rc = new RepeatingCharacters();
		for (String problem : problems) {
			System.out.println("Input: " + problem);
			int ans = rc.lengthOfLongestSubstring(problem);
			System.out.println("Output: " + ans + "\n\n ");
		}
	}

}
