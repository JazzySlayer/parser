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


    public boolean checkAsCondition(String query,String operator,String statement, String function){
        Stack stackCondition = new Stack();
        boolean isCorrect = false;
        if(statement.equals("select")){
            if(function.equals("attr")|| function.equals("tableName")){
                isCorrect = forSelect(query,operator);
            }
            else if(function.equals("condition")){
                isCorrect = forSelectWhere(query,operator);
            }
        }
        return isCorrect;

    }
    private boolean forSelect(String query, String operator){
        Boolean isCorrect = false;
        KeywordChecker keywordChecker =  new KeywordChecker();
        if(keywordChecker.checkWithOperator(operator)){
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
    private boolean forSelectWhere(String query, String operator){
        Boolean isCorrect = false;
        KeywordChecker keywordChecker =  new KeywordChecker();
        if(keywordChecker.checkWithOperator(operator)){
            leftPart = query.split(operator)[0].trim();
            aliasPart = query.split(operator)[1].trim();
            if(operator.equals(keywords.assigningOperator) || operator.equals(keywords.greaterThanOperator)|| operator.equals(keywords.greaterThanOrEqualOperator)
                    || operator.equals(keywords.lessThanOperator)|| operator.equals(keywords.lessThanOrEqualOperator)|| operator.equals(keywords.isNotEqualOperator)){
                if(leftPart.contains("(")){
                    if(leftPart.contains(")")){
                        isCorrect = false;
                    }
                }
                else if(aliasPart.split(" ").length>2 || leftPart.split(" ").length>2){
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
