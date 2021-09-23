package string_Calculator_Test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import string_Calculator.StringCal;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

public class StringCalTest {

	@Test
	public void sumsEmptyStringTo0() {
		assertThat(StringCal.sum(""), is(0));
	}

	@Test
	public void sumsSingleNumberToItself() {
		assertThat(StringCal.sum("4"), is(4));
		assertThat(StringCal.sum("56"), is(56));
	}

	@Test
	public void sumsTwoNumbersSeperatedByComma() {
		assertThat(StringCal.sum("1,5"), is(6));
		assertThat(StringCal.sum("1,4"), is(5));
	}

	@Test
	public void sumsThreeNumbersSeperatedByComma() {
		assertThat(StringCal.sum("1,2,3"), is(6));
	}

	@Test
	public void sumsNumbersDelimitedByNewline() {
		assertThat(StringCal.sum("1\n2"), is(3));
	}

	@Test
	public void sumsNumbersDelimitedByCommaOrNewline() {
		assertThat(StringCal.sum("1,2\n3"), is(6));
	}

	@Test
	public void usesDelimiterSepcified() {
		assertThat(StringCal.sum("//;\n1;2"), is(3));
		assertThat(StringCal.sum("//.\n2.3.1"), is(6));
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void throwsOnNegativeNumber() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("negative number: -3");

		StringCal.sum("-3");
	}

	@Test
	public void throwsOnNegativeNumbersWithAllNumbersInExceptionMessage() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("negative number: -3,-5,-13");

		StringCal.sum("1,-3,5,-5,-13");
	}

	@Test
	public void mapsNumbersAbove1000ToLastThreeDigits() {
		assertThat(StringCal.sum("1002"), is(2));
		assertThat(StringCal.sum("1040,10002"), is(42));
	}

	@Test
	public void acceptsDelimiterOfArbitraryLength() {
		assertThat(StringCal.sum("//[***]\n1***2***3"), is(6));
	}

	@Test
	public void acceptsMultipleDelimiters() {
		assertThat(StringCal.sum("//[-][;]\n1-2;3"), is(6));
		assertThat(StringCal.sum("//[--][...]\n2--3...4"), is(9));
	}
}