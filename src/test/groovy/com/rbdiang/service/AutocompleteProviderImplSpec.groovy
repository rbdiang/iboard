package com.rbdiang.service

import com.rbdiang.model.Candidate
import spock.lang.Specification

/**
 * Created by brian on 6/18/17.
 */
class AutocompleteProviderImplSpec extends Specification {

    private AutocompleteProvider provider = new AutocompleteProviderImpl()


    def "train produces proper suggestion" () {
        given:
        provider.train("foo foo food fork.")

        when:
        List<Candidate> candidates = provider.getWords("f")

        then:
        candidates as Set == [new Candidate('foo', 2), new Candidate('food', 1), new Candidate('fork', 1)] as Set

    }
}
