package com.company.Services;

import com.company.Common.Keywords;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sushant on 12/24/2016.
 */
public class KeywordChecker {
    Keywords keywords = new Keywords();
    public boolean checkWithKeywords(String word){
        boolean answer = false;
        answer = keywords.keywordArray.equals(word);
        System.out.println("Keyword answer = " + answer);
        return answer;
    }

    public boolean checkWithFunctionKeywords(String word){
        boolean answer = false;
        answer = keywords.functionKeywordArray.equals(word);
        System.out.println("Function keyword answer = " + answer);
        return answer;
    }
    public boolean chechWithSpecialCharacter(String word){
        Pattern p = Pattern.compile("[^a-z0-9_]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(word);
        boolean b = m.find();
        return b;
    }
}
