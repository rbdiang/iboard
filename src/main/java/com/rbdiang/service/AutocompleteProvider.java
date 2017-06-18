package com.rbdiang.service;


import com.rbdiang.model.Candidate;

import java.util.List;

public interface AutocompleteProvider {

    /**
     * returns list of candidates ordered by confidence
     *
     * @param fragment to suggest
     * @return recommanded candidates
     */
    List<Candidate> getWords(String fragment);

    /**
     * Train engine's word bank with specified passage
     * @param passage used to train suggestions
     */
    void train(String passage);


}
