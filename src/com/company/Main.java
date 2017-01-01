package com.company;

import com.company.Common.CreateDatabase;
import com.company.Common.CreateTable;
import com.company.Common.Insert;
import com.company.Common.Select;

import java.util.Scanner;

public class Main {
    public static String inputOne="";

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        CreateDatabase createDatabase;
        createDatabase = new CreateDatabase();
        String choiceOwn="";
        do{
            System.out.println("Enter your query:\n");
            inputOne = input.nextLine().toLowerCase();
            divider();


        }   while(false);
//        }   while(choiceOwn != "quit");

        ;
	// write your code here
    }
    public static void divider() throws Exception {
        Boolean answer = true;
        System.out.println("answer = " + inputOne);
        if(inputOne.contains(";")){
            inputOne = inputOne.substring(0,inputOne.indexOf(';'));
        }
        String[] results = inputOne.split(" ",2);
        results[0] = results[0].toLowerCase();
        if(results[0].equals("create")){
            if(results[1].contains("table")){
                CreateTable createTable = new CreateTable();
                answer = createTable.checkCreateTableQuery(inputOne);
                if(answer){
                    System.out.println("Your query is correct :)");
                }
                else{
                    System.out.println("Your query is incorrect!!!!");
                }
                return;
            }
            else if(results[1].contains("database")){
                CreateDatabase createDatabase = new CreateDatabase();
                createDatabase.setUserQuery(inputOne);
                if(createDatabase.check()){
                    System.out.println("Your query is correct :)");
                }
                else{
                    System.out.println("Your query is incorrect!!!!");
                }
            }
            else{
                System.out.println("Error in query statement");
            }
        }
        else if(results[0].equals("select")){
            Select select = new Select();
            select.setUserQuery(results[1]);
            String result = select.tokenization();
            System.out.println("result = " + result);

        }
        else if(results[0]=="insert"){
            Insert insert = new Insert();
            Boolean error = insert.checkInsertIntoQuery(inputOne);
            if(error){
                System.out.println("Your query is correct :)");
            }
            else{
                System.out.println("Your query is incorrect!!!!");
            }
        }
    }


}
