/**
 * 
 */
package com.kant.general.advanced;

/**
 * http://www.geeksforgeeks.org/boggle-find-possible-words-board-characters/
 * 
 * Use a Trie of all words in dictionary ..to get lookahead character and see if
 * it's one of the 8 adjacent cells and then move else stop right there ..word
 * cannot be complete then.
 * 
 * @author shaskant
 *
 */
public class BoggleGame {
	private String dictionary[] = { "GEEKS", "FOR", "QUIZ", "GO" };
	private int N = -1;
	private int M = -1;
	private char[][] boggle;

	/**
	 * 
	 * @param game
	 */
	public BoggleGame(char[][] game) {
		boggle = game;
		M = boggle.length;
		N = boggle[0].length;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[][] game = { { 'G', 'I', 'Z' }, { 'U', 'E', 'K' },
				{ 'Q', 'S', 'E' } };

		BoggleGame gamePlatform = new BoggleGame(game);
		gamePlatform.findWords();

	}

	public void findWords() {
		boolean[][] visited = new boolean[M][];
		for (int i = 0; i < M; i++)
			visited[i] = new boolean[N];
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				findWordsUtil(i, j, visited, buffer);

	}

	/**
	 * DFS
	 */
	private void findWordsUtil(int i, int j, boolean[][] visited,
			StringBuffer output) {
		visited[i][j] = true;
		output.append(boggle[i][j]);

		if (isDictionaryWord(output.toString())) {
			System.out.println(output);
		}

		// all 8 directions
		for (int row = i - 1; row <= i + 1 && row < M; row++)
			for (int col = j - 1; col <= j + 1 && col < N; col++)
				if (row >= 0 && col >= 0 && !visited[row][col])
					findWordsUtil(row, col, visited, output);

		visited[i][j] = false;
		output.deleteCharAt(output.length() - 1);
	}

	boolean isDictionaryWord(String word) {
		for (String item : dictionary) {
			if (item.equals(word))
				return true;
		}
		return false;
	}

}
