package com.example.nlp.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

public class OpenNlpTests {

	String[] sentences = new String[] {
			" Hi. How are you? Welcome to examples. We provide free tutorials on various technologies",
			"Apache openNLP supports the most common NLP tasks, such as tokenization, sentence segmentation, part-of-speech tagging, named entity extraction, chunking, parsing, and coreference resolution. These tasks are usually required to build more advanced text processing services. OpenNLP also includes maximum entropy and perceptron based machine learning." };
	String sentence = sentences[1];

	@BeforeAll
	public void setup() {
		System.out.println("Actual sentence:: " + sentence);
	}

	@Test
	public void simpleSentenceDetect() throws InvalidFormatException, IOException {
		System.out.println("\n\n ******* " + this.getClass().getMethods()[0]);
		String simple = "[.?!]";
		String[] splitString = (sentence.split(simple));
		for (String string : splitString)
			System.out.println("\t\t " + string);
	}

	@Test
	public void nlpSentenceDetect() throws InvalidFormatException, IOException {
		System.out.println("\n\n ******* " + this.getClass().getMethods()[0]);
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("models/en-sent.bin");
		SentenceModel model = new SentenceModel(is);

		// load the model
		SentenceDetectorME sdetector = new SentenceDetectorME(model);

		// detect sentences in the paragraph
		String sentences[] = sdetector.sentDetect(sentence);

		// print the sentences detected, to console
		for (int i = 0; i < sentences.length; i++) {
			System.out.println("line " + i + " :  " + sentences[i]);
		}
		is.close();
	}

	public void nlpToken() {
		System.out.println("\n\n ******* " + this.getClass().getMethods()[1]);
		InputStream modelIn = null;

		try {
			modelIn = this.getClass().getClassLoader().getResourceAsStream("models/en-token.bin");
			TokenizerModel model = new TokenizerModel(modelIn);
			TokenizerME tokenizer = new TokenizerME(model);
			String tokens[] = tokenizer.tokenize("John is 26 years old.");
			double tokenProbs[] = tokenizer.getTokenProbabilities();

			System.out.println("Token\t: Probability\n-------------------------------");
			for (int i = 0; i < tokens.length; i++) {
				System.out.println(tokens[i] + "\t: " + tokenProbs[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
