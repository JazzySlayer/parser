package com.company.Services;

import com.company.Common.Keywords;

/**
 * Created by Sushant on 12/24/2016.
 */
public class KeywordChecker {
    Keywords keywords = new Keywords();
    public boolean checkWithKeywords(String word){
        boolean answer = false;
        answer = keywords.keywordArray.equals(word);
        if(!answer){
            answer = checkWithFunctionKeywords(word);
        }
        System.out.println("answer = " + answer);
        return answer;
    }

    public boolean checkWithFunctionKeywords(String word){
        boolean answer = false;
        answer = keywords.keywordArray.equals(word);
        System.out.println("answer = " + answer);
        return answer;
    }
}
