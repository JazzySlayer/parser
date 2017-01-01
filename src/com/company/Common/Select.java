package com.company.Common;

import com.company.Services.KeywordChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    private List<String> columnAlias = new ArrayList<String>();
    private List<String> column = new ArrayList<String>();

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

    public void tokenization(){
        System.out.println("now here!!!!!!!!!1");
        String error = "";
        if(userQuery.contains("from")){
            attrList = userQuery.split("from",2)[0].trim();
            error = checkAttr();
            if(userQuery.split("from",2)[1].contains("where")){
                String[] separator = userQuery.split("from",2)[1].split("where",2);
                tableName = separator[0].trim();
                condition = separator[1].trim();
                error = checkWithCondition();
            }
            else{
                tableName = userQuery.split("from",2)[1].trim();
                error = checkWithOutCondition();
            }
        }
        else{
            error = "No from was found in the query!!!";

        }






//        return error;
    }
    public String checkAttr(){
        String error = "";
        String[] attrs = attrList.split(",");
        for(String attr: attrs){
            if(attr.split(" ").length >= 2){
                if(attr.split(" ").length == 2){
                    if(attr.split(" ",2)[0].equals("*")||attr.split(" ",2)[1].equals("*") || keywordChecker.checkWithFunctionKeywords(attr.split(" ",2)[0].trim())
                            || keywordChecker.checkWithFunctionKeywords(attr.split(" ",2)[1].trim())||
                            keywordChecker.checkWithKeywords(attr.split(" ",2)[0].trim())||
                            keywordChecker.checkWithKeywords(attr.split(" ",2)[1].trim())){
                        error = "Error near word "+attr;
                        return error;
                    }
                }
                if(attr.contains("as")){
                    Condition condition1 = new Condition("as");
                    condition1.checkAsCondition(attr,"as","select","attr");
                }
            }
            else if(keywordChecker.checkWithFunctionKeywords(attr) || keywordChecker.checkWithKeywords(attr)){
                error = "Error near word " + attr;
                return error;
            }
        }
        return error ;
    }

    public String checkWithCondition(){
        String error = "";
        return error;
    }
    public String checkWithOutCondition(){
        String error = "";
        return error;
    }
    public String checkTableName(){
        String error = "";
        String anotherSelect="", tableN = "";
        Boolean trueTableName = false;
        int i = 0;
        if(tableName.split(" ").length >= 2){
            if(tableName.split(" ").length == 2){
                if(tableName.split(" ",2)[0].equals("*")||tableName.split(" ",2)[1].equals("*") || keywordChecker.checkWithFunctionKeywords(tableName.split(" ",2)[0].trim())
                        || keywordChecker.checkWithFunctionKeywords(tableName.split(" ",2)[1].trim())||
                        keywordChecker.checkWithKeywords(tableName.split(" ",2)[0].trim())||
                        keywordChecker.checkWithKeywords(tableName.split(" ",2)[1].trim())){
                    error = "Error near word "+tableName;
                    return error;
                }
            }
            else if(tableName.split(" ").length > 2 || tableName.contains("as")){
                Condition condition1 = new Condition("as");
                condition1.checkAsCondition(tableName,"as","select","attr");
            }
        }
        return error;
    }


//    public String chectattrList(){
//
//        String error = "",word = "";
//        selectStack = new Stack();
//        String[] attrListsegments = attrList.split(" ");
//        for(String segment: attrListsegments){
//            for(int i = 0;i<attrList.length();i++){
//                if(word.equals("as")){
//
//                }
//                else if(attrList.charAt(i) == ' '){
//                    if(attrList.charAt(i+1) != ' ' && attrList.charAt(i+1) != '(' && attrList.charAt(i+1) != ')'){
//                        word = "";
//                    }
//                    else if(word.equals("distinct") && (attrList.charAt(i) != ' ' || attrList.charAt(i+1) != '(') ){
//                        if(selectStack.contains(word)){
//                            error = String.valueOf(i) + "Misuse of aggreagate function";
//                            break;
//                        }
//                        else{
//
//                            selectStack.push(word);
//                            functionNumber++;
//                            word="";
//                        }
//                    }
//                }
//                else if(attrList.charAt(i) == '('){
//                    if(keywordChecker.checkWithKeywords(word) && !word.equals("distinct")){
//                        error = String.valueOf(i) + "Error it is Keywords used";
//                        break;
//                    }
//                    else if(keywordChecker.checkWithFunctionKeywords(word)){
//                        if(selectStack.contains(word)){
//                            error = String.valueOf(i) + "Misuse of aggreagate function";
//                            break;
//                        }
//                        else{
//
//                            selectStack.push(word);
//                            functionNumber++;
//                            word="";
//                        }
//                    }
//                    else if(attrList.charAt(i+1) == ' '){
//                        if(column.contains(word)){
//                            error = String.valueOf(i) + "Error in column Name";
//                            break;
//                        }
//                        column.add("word");
//                    }
//                }
//                else if(attrList.charAt(i) == ')'){
//                    String poppedWord = "";
//                    poppedWord = selectStack.pop().toString();
//                    if(functionNumber == 2 && poppedWord != "distinct"){
//                        error = String.valueOf(i);
//                        break;
//                    }
//                    functionNumber--;
//                }
//                else{
//                    word += String.valueOf(attrList.charAt(i));
//                }
//
//            }
//            if(!error.isEmpty()){
//                break;
//            }
//        }
//        return error;
//    }
}
