package com.company.Common;

import com.company.Services.KeywordChecker;

/**
 * Created by Sushant on 12/24/2016.
 */
public class CreateDatabase {
    KeywordChecker keywordChecker;
    String databaseName;
    String query_syntax="create database database_name";
    String userQuery;


    public CreateDatabase(){
        databaseName="";
        userQuery="";
    }

    public void setUserQuery(String userQuery){
        this.userQuery=userQuery;

    }

    public boolean check(){
        keywordChecker = new KeywordChecker();;
        Boolean correct = true;
        String temp = userQuery;
        String[] token = new String[3];
        token = temp.split(" ");
        if(token[0].toString().toLowerCase() != "create"){
            correct = false;
            return correct;
        }
        if(token[1].toString().toLowerCase() != "database"){
            correct = false;
            return correct;
        }
        if(keywordChecker.checkWithKeywords(token[2])){
            correct = false;
            return correct;
        }
        if(keywordChecker.checkWithFunctionKeywords(token[2])){
            correct = false;
            return correct;
        }

        return correct;

    }



}
