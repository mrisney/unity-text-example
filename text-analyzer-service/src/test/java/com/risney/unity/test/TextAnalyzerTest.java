package com.risney.unity.test;


import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.risney.unity.text.TextAnalyzer;
import com.risney.unity.text.Word;

public class TextAnalyzerTest {

	@Test
	public void testUniqueWords() throws Exception {
		
		String text = getFile("test.txt");
		String[] words = text.split("\\s+");
		String[] uniqueWords = TextAnalyzer.getUniqueWords(words);
		assertEquals(uniqueWords.length, 10);

		String[] noWords = TextAnalyzer.getUniqueWords(null);
		assertEquals(noWords.length, 0);
		
		String[] oneWord = new String[] { "one", "one", null };
		String[] uniqueWordsOne = TextAnalyzer.getUniqueWords(oneWord);
		assertEquals(uniqueWordsOne.length, 1);
		
		String[] nullText = new String[]{null};
		String[] uniqueWordsZero = TextAnalyzer.getUniqueWords(nullText);
		assertEquals(uniqueWordsZero.length, 0);

		String[] uniqueWordsNull = TextAnalyzer.getUniqueWords(null);
		assertEquals(uniqueWordsNull.length, 0);		
	}

	@Test
	public void testAnalyzeWords() throws Exception {
		String text = getFile("test.txt");
		List<Word> testWords = TextAnalyzer.analyzeWords(text);

		assertThat(testWords,
				hasItems(new Word("The", 3, 1),
					new Word("quick", 5, 1),
					new Word("brown", 5, 2),
					new Word("fox", 3, 1),
					new Word("jumped", 6, 1),
					new Word("over", 4, 1),
					new Word("the", 3, 1),
					new Word("lazy", 4, 1),
					new Word("dog’s", 5, 1),
					new Word("back", 4, 1)));

		String mobyDickChapter = getFile("mobydick.txt");
		List<Word> melvilleWords = TextAnalyzer.analyzeWords(mobyDickChapter);

		assertThat(melvilleWords,
				hasItems(new Word("Call", 4, 1),
					new Word("me", 2, 24),
					new Word("Ishmael", 7, 1),
					new Word("whaling", 7, 4)));
				
		String nullText = null;
		List<Word> zeroWords = TextAnalyzer.analyzeWords(nullText);
		assertEquals(zeroWords.size(), 0);
	}

	@Test
	public void testSorting() throws Exception {
		String text = getFile("test.txt");
		List<Word> wordList = TextAnalyzer.analyzeWords(text);
		wordList = TextAnalyzer.sortByASCII(wordList);
		wordList = TextAnalyzer.sortByLengths(wordList);

		assertThat(wordList,
				contains(new Word("The", 3, 1),
					new Word("fox", 3, 1),
					new Word("the", 3, 1),
					new Word("back", 4, 1),
					new Word("lazy", 4, 1),
					new Word("over", 4, 1),
					new Word("brown", 5, 2),
					new Word("dog’s", 5, 1),
					new Word("quick", 5, 1),
					new Word("jumped", 6, 1)));

		String orderedText = getFile("order.txt");
		List<Word> orderedWordList = TextAnalyzer.analyzeWords(orderedText);
		List<Word> countedSortWordList = TextAnalyzer.sortByCounts(orderedWordList);

		assertThat(countedSortWordList, 
				hasItems(new Word("a", 1, 1),
					new Word("BBB", 3, 3),
					new Word("DD", 2, 6)));
	}

	private String getFile(String fileName) {
		String result = "";
		try {
			result = IOUtils.toString(this.getClass().getResourceAsStream("/"+fileName), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}