package com.risney.unity.test;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.risney.unity.rest.TextAnalyzerController;

@RunWith(SpringRunner.class)
@WebMvcTest(TextAnalyzerController.class)
public class TextAnalyzerControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void analyzeTest() throws Exception {

		mvc.perform(post("/analyze").content("The quick brown fox jumped over the lazy brown dog’s back")
				.contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(10)))
				.andExpect(jsonPath("$[2].word", is("brown")))
				.andExpect(jsonPath("$[2].length", is(5)))
				.andExpect(jsonPath("$[2].count", is(2)));
				//.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void analyzeAndSortTest() throws Exception {
		mvc.perform(post("/analyze-sort").content("The quick brown fox jumped over the lazy brown dog’s back")
				.contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(10)))
				.andExpect(jsonPath("$[0].word", is("The")))
				.andExpect(jsonPath("$[9].word", is("jumped")));
	}


	@Test
	public void analyzeAndSortByCountTest() throws Exception {

		mvc.perform(post("/analyze-sort-count").content("The quick brown fox jumped over the lazy brown dog’s back")
				.contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(10)))
				.andExpect(jsonPath("$[0].word", is("The")))
				.andExpect(jsonPath("$[9].word", is("brown")))
				.andExpect(jsonPath("$[9].length", is(5)))
				.andExpect(jsonPath("$[9].count", is(2)));
	}
	
	@Test
	public void getUniqueWordsTest() throws Exception {

		mvc.perform(post("/unique-words").content("The quick brown fox jumped over the lazy brown dog’s back over the brown road")
				.contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(11)));
	}
}
