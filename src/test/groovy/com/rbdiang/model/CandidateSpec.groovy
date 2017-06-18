package com.rbdiang.model

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by brian on 6/17/17.
 */
class CandidateSpec extends Specification {

    @Unroll
    "candidate is printed correctly for #c" () {

        expect:
        Candidate candidate = c as Candidate

        candidate.toString() == expected


        where:
        c                                   || expected
        [word: "hello", confidence: 5]      || '"hello" (5)'
        [word: "foo", confidence: 1]        || '"foo" (1)'
        [word: "f", confidence: 10]         || '"f" (10)'

    }

    def "confidence can be increased" () {
        given:
        Candidate c = new Candidate("foo", 1)

        assert c.confidence == 1

        when:
        c.increaseConfidence()

        then:
        c.word == "foo"
        c.confidence == 2

    }
}
