package com.rbdiang.controllers;

import com.rbdiang.model.Candidate;
import com.rbdiang.service.AutocompleteProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by brian on 6/18/17.
 */
@RestController
public class SuggestController {

    @Autowired
    AutocompleteProvider suggestProvider;

    @RequestMapping(value = "/train", method = RequestMethod.POST)
    public void trainProvider(@RequestBody String passage) {
        suggestProvider.train(passage);
    }

    @RequestMapping("/suggest/{fragment}")
    public List<String> getSuggestions(@PathVariable(name = "fragment") String fragment) {

        return Arrays.stream(fragment.toLowerCase().split("\\s"))
                .flatMap(f -> suggestProvider.getWords(f).stream())
                .map(Candidate::toString)
                .collect(Collectors.toList());
//        return suggestProvider
//                .getWords(fragment)
//                .stream()
//                .map(Candidate::toString)
//                .collect(Collectors.toList());
    }

}
