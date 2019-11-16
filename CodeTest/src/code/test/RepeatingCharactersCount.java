package code.test;

/**
 * @author Krishna Manchikalapudi
 * 
 *         Problem: prints number of occurrences of each characters
 *
 */
public class RepeatingCharactersCount {

	private void occuringCharCount(String input) {
		int count[] = new int[256]; // ASCII_SIZE

		// Initialize count array index
		for (int i = 0; i < input.length(); i++) {
			count[input.charAt(i)]++;
		} // for (int i = 0; i < input.length(); i++)

		// Create an array of given String size
		char ch[] = new char[input.length()];
		for (int i = 0; i < input.length(); i++) {
			ch[i] = input.charAt(i);
			int find = 0;
			for (int j = 0; j <= i; j++) {
				// If any matches found
				if (input.charAt(i) == ch[j])
					find++;
			} // for (int j = 0; j <= i; j++)

			if (find == 1)
				System.out.println("Number of occurrence: char '" + input.charAt(i) + "' is " + count[input.charAt(i)]);
		} // for (int i = 0; i < input.length(); i++)
	}

	public static void main(String[] args) {
		String[] problems = new String[] { "aa", "abcabecbb", "aabcdeeab" };
		RepeatingCharactersCount rc = new RepeatingCharactersCount();
		for (String problem : problems) {
			System.out.println("Input: " + problem);
			rc.occuringCharCount(problem);
			System.out.println("\n");
		}
	}

}
