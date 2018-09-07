package com.risney.unity.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.risney.unity.text.TextAnalyzer;
import com.risney.unity.text.Word;

@RestController
public class TextAnalyzerController {

	@RequestMapping(value = "/analyze", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Word>> analyze(@RequestBody String text) {

		List<Word> analyzedWords = TextAnalyzer.analyzeWords(text);
		return new ResponseEntity<List<Word>>(analyzedWords, HttpStatus.OK);
	}

	@RequestMapping(value = "/analyze-sort", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Word>> analyzeAndSort(@RequestBody String text) {

		List<Word> analyzedWords = TextAnalyzer.analyzeWords(text);

		analyzedWords = TextAnalyzer.sortByASCII(analyzedWords);
		analyzedWords = TextAnalyzer.sortByLengths(analyzedWords);

		return new ResponseEntity<List<Word>>(analyzedWords, HttpStatus.OK);

	}

	@RequestMapping(value = "/analyze-sort-count", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Word>> analyzeAndSortByCount(@RequestBody String text) {

		List<Word> analyzedWords = TextAnalyzer.analyzeWords(text);

		analyzedWords = TextAnalyzer.sortByCounts(analyzedWords);
		return new ResponseEntity<List<Word>>(analyzedWords, HttpStatus.OK);

	}

	@RequestMapping(value = "/unique-words", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<String>> getUniqueWords(@RequestBody String text) {

		String[] splitWords = text.replaceAll("[\\p{Punct}&&[^`'-]]+", "").split("\\s+");
		String[] uniqueWords = TextAnalyzer.getUniqueWords(splitWords);

		return new ResponseEntity<List<String>>(Arrays.asList(uniqueWords), HttpStatus.OK);
	}
}
