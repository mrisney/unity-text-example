package com.risney.unity.text;

public class Word {

	private String word = "";
	private int length = 0;
	private int count = 0;

	public Word(String word) {
		super();
		this.word = word;
		this.length = word.length();
		this.count = 1;
	}

	public Word(String word, int length, int count) {
		super();
		this.word = word;
		this.length = length;
		this.count = count;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (count != other.count)
			return false;
		if (length != other.length)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + length;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Word [word=" + word + ", length=" + length + ", count=" + count + "]";
	}

}