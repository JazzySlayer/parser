package com.company.Common;

import com.company.Services.KeywordChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Sushant on 12/24/2016.
 */
public class Select {

    private int functionNumber = 0;
    private Stack selectStack = null;
    private String userQuery="";
    private String attrList="";
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
        attrList = userQuery.split("from",2)[0].trim();
        if(userQuery.split("from",2)[1].contains("where")){
            String[] separator = userQuery.split("from",2)[1].split("where",2);
            tableName = separator[0].trim();
            condition = separator[1].trim();
        }
        else{
             tableName = userQuery.split("from",2)[1].trim();
        }


//        return error;
    }

    public String chectattrList(){
        KeywordChecker keywordChecker = new KeywordChecker();
        String error = "",word = "";
        selectStack = new Stack();
        String[] attrListsegments = attrList.split(" ");
        for(String segment: attrListsegments){
            for(int i = 0;i<attrList.length();i++){
                if(word.equals("as")){

                }
                else if(attrList.charAt(i) == ' '){
                    if(attrList.charAt(i+1) != ' ' && attrList.charAt(i+1) != '(' && attrList.charAt(i+1) != ')'){
                        word = "";
                    }
                    else if(word.equals("distinct") && (attrList.charAt(i) != ' ' || attrList.charAt(i+1) != '(') ){
                        if(selectStack.contains(word)){
                            error = String.valueOf(i) + "Misuse of aggreagate function";
                            break;
                        }
                        else{

                            selectStack.push(word);
                            functionNumber++;
                            word="";
                        }
                    }
                }
                else if(attrList.charAt(i) == '('){
                    if(keywordChecker.checkWithKeywords(word) && !word.equals("distinct")){
                        error = String.valueOf(i) + "Error it is Keywords used";
                        break;
                    }
                    else if(keywordChecker.checkWithFunctionKeywords(word)){
                        if(selectStack.contains(word)){
                            error = String.valueOf(i) + "Misuse of aggreagate function";
                            break;
                        }
                        else{

                            selectStack.push(word);
                            functionNumber++;
                            word="";
                        }
                    }
                    else if(attrList.charAt(i+1) == ' '){
                        if(column.contains(word)){
                            error = String.valueOf(i) + "Error in column Name";
                            break;
                        }
                        column.add("word");
                    }
                }
                else if(attrList.charAt(i) == ')'){
                    String poppedWord = "";
                    poppedWord = selectStack.pop().toString();
                    if(functionNumber == 2 && poppedWord != "distinct"){
                        error = String.valueOf(i);
                        break;
                    }
                    functionNumber--;
                }
                else{
                    word += String.valueOf(attrList.charAt(i));
                }

            }
            if(!error.isEmpty()){
                break;
            }
        }
        return error;
    }
}
