import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LeerFicheroTest {

	@Test
	public void  test1NumberWords() {
		String description = "hola hola hola ";
		int result = LeerFichero.numberWords(description);
		int expected = 3;
		assertEquals(expected, result);
		
		description = "hola hola hola ";
		result = LeerFichero.numberWords(description);
		expected = 3;
		assertEquals(expected, result);
		
		description = " hola hola hola ";
		result = LeerFichero.numberWords(description);
		expected = 3;
		assertEquals(expected, result);
		
		description = " ";
		result = LeerFichero.numberWords(description);
		expected = 0;
		assertEquals(expected, result);
	}
	
	@Test
	public void testCleanSpaceAtBeginning() {
		String description = "      hola";
		String result = LeerFichero.cleanSpaces(description);
		String expected ="hola";
		assertEquals(expected, result);
	}
	
	@Test
	public void testCleanSpaceAtEnd() {
		String description = "hola       ";
		String result = LeerFichero.cleanSpaces(description);
		String expected = "hola";
		assertEquals(expected, result);
	}
	
	@Test
	public void testRemoveSymbols() {
		String description = "hola-cara/cola";
		String result = LeerFichero.removeCharacters(description);
		String expected = "hola-cara-cola";
		assertEquals(expected, result);
	}
	
	@Test
	public void testsetLowCase() {
		String description = "HoLA";
		String result = LeerFichero.setLowCase(description);
		String expected = "hola";
		assertEquals(expected, result);
	}
	
	@Test
	public void test1ExtractWords() {
		String description ="hola hola hola";
		List<String> result= LeerFichero.extractWords(description);
		List<String> expected = Arrays.asList("hola","hola","hola");
		assertThat(result, is(expected));
	}
	
	@Test
	public void test2ExtractWords() {
		String description =" ";
		List<String> result= LeerFichero.extractWords(description);
		List<String> expected = Arrays.asList(" ");
		assertThat(result, is(expected));
	}
	
	@Test
	public void testCompareLists() {
		List<String> list1 = Arrays.asList("a","b","c");
		List<String> list2 = Arrays.asList("b","c","a");
		boolean result= LeerFichero.compareListsOfWords(list1, list2);
		boolean expected = true;
		assertEquals(result,expected);
	}

	@Test
	public void testExtractDifferentWords() {
		List<String> list1 = Arrays.asList("a","b","d");
		List<String> list2 = Arrays.asList("b","c","g");
		List <String> result = LeerFichero.extractDifferentWords(list1, list2);
		List<String> expected = Arrays.asList("a","d","c","g");
		assertThat(result,is(expected));
	}
}
