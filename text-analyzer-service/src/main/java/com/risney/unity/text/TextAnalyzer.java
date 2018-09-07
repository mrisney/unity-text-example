package com.risney.unity.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TextAnalyzer {

	public static List<Word> analyzeWords(String text) {

		if (text == null) {
			return new ArrayList<Word>();
		}

		// remove punctuation except back tick, apostrophe, and dash
		String[] words = text.replaceAll("[\\p{Punct}&&[^`'-]]+", "").split("\\s+");
		String[] uniqueWords = getUniqueWords(words);

		Word[] result = new Word[uniqueWords.length];

		int i = 0;
		for (String word : uniqueWords) {
			for (String s : words) {
				if (word.equals(s)) {
					if (result[i] == null) {
						result[i] = new Word(word);
					} else {
						Word existingWord = result[i];
						existingWord.setCount(existingWord.getCount() + 1);
					}
				}
			}
			i++;
		}
		return Arrays.asList(result);
	}

	public static String[] getUniqueWords(String[] words) {

		if (words == null) {
			return new String[0];
		}

		words = Arrays.stream(words).filter(Objects::nonNull).toArray(String[]::new);

		String[] uniqueWords = new String[words.length];
		if (words.length > 0) uniqueWords[0] = words[0];

		int uniqueKeyIndex = 1;
		boolean wordExists = false;

		for (int i = 1; i < words.length; i++) {
			for (int j = 0; j <= uniqueKeyIndex; j++) {
				if (words[i].equals(uniqueWords[j])) {
					wordExists = true;
				}
			}

			if (!wordExists) {
				uniqueWords[uniqueKeyIndex] = words[i];
				uniqueKeyIndex++;
			}
			wordExists = false;
		}
		return Arrays.stream(uniqueWords).filter(Objects::nonNull).toArray(String[]::new);
	}

	public static List<Word> sortByASCII(List<Word> wordList) {

		wordList.sort(new Comparator<Word>() {
			@Override
			public int compare(Word wordOne, Word wordTwo) {
				return wordOne.getWord().compareTo(wordTwo.getWord());
			}
		});
		return wordList;
	}

	public static List<Word> sortByLengths(List<Word> wordList) {

		wordList.sort(new Comparator<Word>() {
			@Override
			public int compare(Word wordOne, Word wordTwo) {
				return wordOne.getLength() - wordTwo.getLength();
			}
		});
		return wordList;
	}

	public static List<Word> sortByCounts(List<Word> wordList) {

		wordList.sort(new Comparator<Word>() {
			@Override
			public int compare(Word wordOne, Word wordTwo) {
				return wordOne.getCount() - wordTwo.getCount();
			}
		});
		return wordList;
	}
}