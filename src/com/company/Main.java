package com.company;

import com.company.Common.CreateDatabase;

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
            inputOne = input.nextLine();
            divider();


        }   while(false);
//        }   while(choiceOwn != "quit");

        ;
	// write your code here
    }
    public static boolean divider(){
        Boolean answer = true;
        String[] results = inputOne.split(" ",2);
        if(results[0]=="create"){
            CreateDatabase createDatabase = new CreateDatabase();
            createDatabase.setUserQuery(inputOne);
            if(createDatabase.check()){
                System.out.println("Your query is correct :)");
            }
            else{
                System.out.println("Your query is incorrect!!!!");
            }
        }
        else if(results[0]=="select"){

        }
        else if(results[0]=="insert"){

        }
        return answer;
    }
}
