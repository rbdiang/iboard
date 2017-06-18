package com.rbdiang.service;

import com.rbdiang.model.Candidate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by brian on 6/17/17.
 */
@Component
public class AutocompleteProviderImpl implements AutocompleteProvider {

    private Map<String, Map<String, Candidate>> wordBank = new ConcurrentHashMap<>();

    public List<Candidate> getWords(String frag) {
        final String fragment = frag.toLowerCase();
        if (wordBank.containsKey(fragment)){
            return wordBank.get(fragment)
                    .values()
                    .stream()
                    .sorted(Comparator.comparing(Candidate::getConfidence).reversed())
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    public void train(String passage) {
        if (passage == null) {
            throw new IllegalArgumentException("passage must not be null");
        }

        wordBank.clear();

        Arrays.stream(passage.toLowerCase().split("\\s"))
                .forEach(w -> {
                    final String word = w.replaceAll("\\W", "");
                    final StringBuilder wordFragment = new StringBuilder();
                    word.chars()
                            .mapToObj(i -> (char) i)
                            .forEach(c -> {
                                wordFragment.append(c);
                                String frag = wordFragment.toString();

                                Map<String, Candidate> suggestMap = wordBank.get(frag);
                                if (suggestMap == null) {
                                    wordBank.put(frag, new LinkedHashMap<String, Candidate>() {{
                                        put(word, new Candidate(word, 1));
                                    }});
                                } else {
                                    Candidate candidate = suggestMap.get(word);
                                    if (candidate == null) {
                                        suggestMap.put(word, new Candidate(word, 1));
                                    } else {
                                        candidate.increaseConfidence();
                                    }
                                }

                            });

                });
    }
}

