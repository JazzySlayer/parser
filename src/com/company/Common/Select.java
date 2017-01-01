package com.company.Common;

import com.company.Services.KeywordChecker;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sushant on 12/24/2016.
 */
public class Select {
    KeywordChecker keywordChecker = new KeywordChecker();
    private int functionNumber = 0;
    private Stack selectStack = null;
    private String userQuery="";
    private String attrList="";
    private String originalTableName = "";
    private String tableName="";
    private String condition="";
    private String tableAlias="";
    Pattern p = Pattern.compile("[^a-z0-9_ ]", Pattern.CASE_INSENSITIVE);

    public void setUserQuery(String userQuery) {
        this.userQuery = userQuery;
    }

    public void setAttrList(String attrList) {
        this.attrList = attrList;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String tokenization(){
        System.out.println("now here!!!!!!!!!1");
        String error = "";
        if(userQuery.contains("from")){
            attrList = userQuery.split("from",2)[0].trim();
            error = checkAttr();
            if(!error.isEmpty()){
                return error;
            }
            if(userQuery.split("from",2)[1].contains("where")){
                String[] separator = userQuery.split("from",2)[1].split("where",2);
                tableName = separator[0].trim();
                condition = separator[1].trim();
                error = checkWithCondition();
                return error;
            }
            else{
                tableName = userQuery.split("from",2)[1].trim();
                error = checkWithOutCondition();
                return error;
            }
        }
        else{
            error = "No from was found in the query!!!";

        }
        return error;
//        return error;
    }

    public String checkAttr(){
        String error = "";
        String[] attrs = attrList.split(",");

        for(String attr: attrs){
            if(attr.split(" ").length==1){
                if(!attr.equals("*") && checkAllKeywords(attr)){
                    error = "1Error near word "+ attr +"!! It is like used of keywords";
                    return error;
                }
                else if(attr.equals("")){
                    error = "2The retrieving column is empty!!!";
                            return error;
                }
            }
            else if(attr.split(" ").length > 1){
                String word = "";
                Stack attrStack = new Stack();
                for(int i = 0;i<attr.length();i++){
                    Matcher m = p.matcher(String.valueOf(attr.charAt(i)));
                    if(attr.charAt(i) == ' ' || (i+1)==attr.length()){
                        if(!word.isEmpty()){
                            word+= String.valueOf(attr.charAt(i));
                            attrStack.push(word);
                            word = "";
                        }
                    }
                    else if(!m.find() || attr.charAt(i) =='*'){
                        word+= String.valueOf(attr.charAt(i));
                    }
                    else{
                        error = "3Error near word" + attr;
                        return error;
                    }

                }
                String[] column = new String[attrStack.size()];
                for(int i = attrStack.size();i>0;i--){
                    column[i-1]=attrStack.pop().toString();
                    System.out.println( "pop  = " + column[i-1]);
                }
                if(column.length == 2){
                    if(checkAllKeywords(column[0]) ){
                        error = "51 Error in word "+ column[0] +"!! It is like used of keywords";
                        return error;
                    }
                    else{
                        if(checkAllKeywords(column[1])){
                            error = "52 Error in word "+ column[1] +"!! It is like used of keywords";
                            return error;
                        }
                    }

                }
                else if(column.length == 3){
                    if(column[1].equals("as")){
                        if(checkAllKeywords(column[0]) || checkAllKeywords(column[2])){
                            error = "6Error in word "+column[1] +"!! It is like used of keywords";
                            return error;
                        }
                    }
                    else{
                        error ="7Error in word "+ column[0] + column[1] + column[2] + "!! It is like one of is keywords or the special character is used\"" ;
                        return error;
                    }
                }
                else if(column.length  > 3){
                    error = "8Error in word" + column[0] + column[1] + column[2] + "!!Excessive variable";
                    return error;
                }
            }

        }
        System.out.println("Attribut error = " + error);
        return error ;
    }

    public String checkWithOutCondition(){
        String error = "";
        error = checkTableName();
        return error;
    }
    public String checkWithCondition(){
        String error = "";
        error = checkTableName();
        if(!error.isEmpty()){
            return error;
        }
        error = checkAllCondition();
        return error;
    }
    public String checkTableName(){
        String error = "";
        String anotherSelect="", tableN = "";
        Boolean trueTableName = false;
        int i = 0;
        if(tableName.split(" ").length >= 2){
            if(tableName.split(" ").length == 2){
                if(checkAllKeywords(tableName.split(" ",2)[0].trim())){
                    error = "Error near word "+tableName.split(" ",2)[0].trim() + "!!! It is like used of keywords";
                    return error;
                }
                else if (tableName.split(" ",2)[0].trim().equals("*")){
                    error = "Error near word "+tableName.split(" ",2)[0].trim() ;
                    return error;
                }
                else if (checkAllKeywords(tableName.split(" ",2)[1].trim())){
                    error = "Error near word "+tableName.split(" ",2)[1].trim() + "!!! It is like used of keywords";
                    return error;
                }
                else if (tableName.split(" ",2)[0].trim().equals("*")){
                    error = "Error near word "+tableName.split(" ",2)[1].trim() ;
                    return error;
                }
            }
            else if(tableName.split(" ").length > 2 && tableName.contains("as")){
                Condition condition1 = new Condition("as");
                condition1.checkAsCondition(tableName,"as","select","attr");
            }

        }
        else if(tableName.split(" ").length == 1){
            if(tableName.isEmpty()){
                error = "Error no table selected!!";
                return error;
            }
        }
        return error;
    }
    public boolean checkAllKeywords(String word){
        boolean result = false;
        if(keywordChecker.checkWithKeywords(word) || keywordChecker.checkWithFunctionKeywords(word) || keywordChecker.chechWithSpecialCharacter(word)){
            result = true;
        }
        return result;
    }
    public String checkAllCondition(){
        String error = "";
        String word = "";
        Stack attrStack = new Stack();
        for(int i = 0;i<condition.length();i++){

            Matcher m = p.matcher(String.valueOf(condition.charAt(i)));
            if(condition.charAt(i) == ' ' || (i+1)==condition.length()){
                if(!word.isEmpty()){
                    word+= String.valueOf(condition.charAt(i));
                    attrStack.push(word);
                    word = "";
                }
            }
            else if(!m.find()){
                word+= String.valueOf(condition.charAt(i));
            }

        }
        if(attrStack.size()==3){
            String[] column = new String[attrStack.size()];
            for(int i = attrStack.size();i>0;i--){
                column[i-1]=attrStack.pop().toString();
                System.out.println( "pop  = " + column[i-1]);
            }
            Condition condition1 = new Condition("as");
            condition1.checkAsCondition(column[0]+" "+ column[1] + " " + column[2],column[1],"select","condition");

        }
        else{
            error = "Error near word " + condition;
            return error;

        }
        return error;
    }



}
