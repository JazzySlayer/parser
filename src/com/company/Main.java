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


        }   while(false);
//        }   while(choiceOwn != "quit");

        ;
	// write your code here
    }
    public static boolean divider(){
        Boolean answer = true;
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
            System.out.println("Entered in Select !!!");
            Select select = new Select();
            select.setUserQuery(results[1]);
            select.tokenization();

        }
        else if(results[0]=="insert"){

        }
        return answer;
    }
}
