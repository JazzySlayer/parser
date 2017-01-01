package com.company;

import com.company.Common.CreateDatabase;
import com.company.Common.Select;

import java.util.Scanner;

public class Main {
    public static String inputOne="";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CreateDatabase createDatabase;
        createDatabase = new CreateDatabase();
        String choiceOwn="";
        do{
            System.out.println("Enter your query:\n");
            inputOne = input.nextLine().toLowerCase();
            divider();

            try {
                if (checkCreateTableQuery(inputOne) || checkInsertIntoQuery(inputOne))
                    System.out.println("Query is correct");
                else
                    System.out.println("Query is incorrect");
            }catch(Exception e){

                System.out.println("Query is incorrect");
            }


        }   while(false);
//        }   while(choiceOwn != "quit");

        ;
	// write your code here
    }
    public static boolean divider(){
        Boolean answer = true;
        System.out.println("answer = " + inputOne);
        if(inputOne.contains(";")){
            inputOne = inputOne.substring(0,inputOne.indexOf(';'));
        }
        String[] results = inputOne.split(" ",2);
        results[0] = results[0].toLowerCase();
        if(results[0].equals("create")){
            CreateDatabase createDatabase = new CreateDatabase();
            createDatabase.setUserQuery(inputOne);
            if(createDatabase.check()){
                System.out.println("Your query is correct :)");
            }
            else{
                System.out.println("Your query is incorrect!!!!");
            }
        }
        else if(results[0].equals("select")){
            Select select = new Select();
            select.setUserQuery(results[1]);
            String result = select.tokenization();
            System.out.println("result = " + result);

        }
        else if(results[0]=="insert"){

        }
        return answer;
    }
    public static boolean checkCreateTableQuery(String query) throws Exception{

        if(!query.contains("create"))
            return false;

        String queryFirstPart = query.substring(0, query.indexOf('(')).toLowerCase();
        String queryVariablesPart = query.substring(query.indexOf('(') + 1, query.lastIndexOf(')')).toLowerCase();

        if(queryFirstPart.matches("^create table \\w+")){

//            for variables part
            if(queryVariablesPart.contains(",")){

                String variables[] = queryVariablesPart.split(",");

                for(String variable: variables){

                    if(!variable.trim().matches("\\w+ ((?:varchar)\\(\\d+\\)|int)"))
                        return false;
                }
            }else{

                if(!queryVariablesPart.trim().matches("\\w+ ((?:varchar)\\(\\d+\\)|int)"))
                    return false;
            }

            return true;
        }

        return false;
        //tests
        //create table tableName(v1 int, v2 varchar(100));
        //create table tableName(v1 int not null v2 varchar(1));
        //create table tableName(v1 int not null, v2 varchar());
        //create table tableName(v1 int not null, v2 varchar);
        //create table tableName(v1 v2 varchar);
    }

    public static boolean checkInsertIntoQuery(String query) throws Exception{

        if(!query.contains("insert"))
            return false;

        String queryFirstPart = query.substring(0, query.indexOf('(')).toLowerCase();
//        System.out.println(queryFirstPart);

        if(queryFirstPart.matches("^insert into \\w+ values")){
            String queryValuesPart = query.substring(query.indexOf('(') + 1, query.lastIndexOf(')'));
            System.out.println(queryValuesPart);

//          for variables part
            if(queryValuesPart.contains(",")){

                String values[] = queryValuesPart.split(",");

                for(String value: values){

                    if(!value.trim().matches("\\d+|\\'\\w*\\'"))
                        return false;
                }
            }else{

                if(!queryValuesPart.trim().matches("\\d+|\\'\\w*\\'"))
                    return false;
            }

            return true;
        }else if(queryFirstPart.matches("^insert into \\w+")){

            String queryVariablesPart = query.substring(query.indexOf('(') + 1, query.indexOf(" values") - 1);
            String queryValuesPart = query.substring(query.indexOf("values") + 7, query.lastIndexOf(')'));
            System.out.println(queryVariablesPart);
            System.out.println(queryValuesPart);

//          for variables part
            if(queryVariablesPart.contains(",") && queryValuesPart.contains(",")){

                String variables[] = queryVariablesPart.split(",");
                String values[] = queryValuesPart.split(",");

                if(variables.length != values.length)
                    return false;

                for(String value: values){

                    if(!value.trim().matches("\\d+|\\'\\w*\\'"))
                        return false;
                }
            }else{

                if(!queryValuesPart.trim().matches("\\d+|\\'\\w*\\'"))
                    return false;
            }

            return true;
        }else {

            if (!queryFirstPart.contains("into"))
                System.err.println("into is missing");

            if (!queryFirstPart.contains("values"))
                System.err.println("values is missing");
        }

        return false;
        //tests
        //insert into tableName() values();
        //insert into tableName(v1) values('');
        //insert into tableName values('');
        //insert into tableName(v1, v2, v3, v4) values(1, 2, 3, 4);
        //insert into tableName(v1, v2, v3, v4) values(1, 'asdfasdf', 3, 'asdfasdf');
        //insert into tableName values(1, 'asdfasf', 3, 'asasdfasdf')
        //insert into tableName values('asasdfasdf')
        //insert into tableName values(1, 'asdfasf', 3, 'asasdfasdf'
        //insert into tableName values(3)
    }
}
