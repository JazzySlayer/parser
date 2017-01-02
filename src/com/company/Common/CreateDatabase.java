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
        token = temp.split(" ",3);
        if(!token[0].equals("create")){
            correct = false;
            return correct;
        }
        if(!token[1].equals("database")){
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
        if(token[2].split(" ").length > 1){
            correct = false;
            return correct;
        }

        return correct;

    }



}
