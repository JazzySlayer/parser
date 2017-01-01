package com.company.Common;

/**
 * Created by Sushant on 1/1/2017.
 */
public class CreateTable {
    public boolean checkCreateTableQuery(String query) throws Exception{

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
}
