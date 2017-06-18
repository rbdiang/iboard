package com.rbdiang.model;

/**
 * Created by brian on 6/17/17.
 */
public class Candidate {
    private String word;
    private Integer confidence;

    public Candidate() {
    }

    public Candidate(String word, Integer confidence) {
        this.word = word;
        this.confidence = confidence;
    }

    public void increaseConfidence() {
        confidence += 1;

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;

        Candidate candidate = (Candidate) o;

        if (getWord() != null ? !getWord().equals(candidate.getWord()) : candidate.getWord() != null) return false;
        return getConfidence() != null ? getConfidence().equals(candidate.getConfidence()) : candidate.getConfidence() == null;
    }

    @Override
    public int hashCode() {
        int result = getWord() != null ? getWord().hashCode() : 0;
        result = 31 * result + (getConfidence() != null ? getConfidence().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format ("\"%s\" (%d)", word, confidence);
    }
}
