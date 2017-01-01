package com.company.Common;

import com.company.Services.KeywordChecker;

import java.util.Stack;

/**
 * Created by Sushant on 12/31/2016.
 */
public class Condition {
    Keywords keywords = new Keywords();
    private String leftPart = "";
    private String operat = "";
    private String aliasPart = "";

    public Condition(String operator){
        operat = operator;
    }
    public boolean checkWithOperator(){
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

    public boolean checkAsCondition(String query,String operator,String statement, String function){
        Stack stackCondition = new Stack();
        boolean isCorrect = false;
        if(statement.equals("select")){
            if(function.equals("attr")|| function.equals("tableName")){
                isCorrect = forSelect(query,operator);
            }
        }
        return isCorrect;

    }
    private boolean forSelect(String query, String operator){
        Boolean isCorrect = false;
        KeywordChecker keywordChecker =  new KeywordChecker();
        if(checkWithOperator()){
            leftPart = query.split(operator)[0].trim();
            aliasPart = query.split(operator)[1].trim();
            if(operator.equals(keywords.assigningOperator)){
                if(leftPart.contains("(")){
                    if(leftPart.contains(")")){
                        isCorrect = true;
                    }
                }
                else if(aliasPart.split(" ").length==1 || leftPart.split(" ").length==1){
                    isCorrect = true;
                }
                else if(!keywordChecker.checkWithFunctionKeywords(leftPart) || !keywordChecker.checkWithKeywords(leftPart )||
                        !keywordChecker.checkWithFunctionKeywords(aliasPart) || !keywordChecker.checkWithKeywords(aliasPart )){
                    isCorrect = true;
                }
            }
        }
        else{
            isCorrect = false;
        }

         return isCorrect;
    }
}
