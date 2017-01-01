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
        return answer;
    }

    public boolean checkWithFunctionKeywords(String word){
        boolean answer = false;
        answer = keywords.functionKeywordArray.equals(word);
        return answer;
    }
    public boolean chechWithSpecialCharacter(String word){
        Pattern p = Pattern.compile("[^a-z0-9_]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(word);
        boolean b = m.find();
        return b;
    }
    public boolean checkWithOperator(String operat){
        if(operat.equals(keywords.assigningOperator)) { return true;}
        else if(operat.equals(keywords.greaterThanOperator)) { return true;}
        else if(operat.equals(keywords.greaterThanOrEqualOperator)) { return true;}
        else if(operat.equals(keywords.lessThanOperator)) { return true;}
        else if(operat.equals(keywords.lessThanOrEqualOperator)) { return true;}
        else if(operat.equals(keywords.isNotEqualOperator)) { return true;}
        else{
            return false;
        }
    }
}
