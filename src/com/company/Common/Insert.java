package com.company.Common;

/**
 * Created by Sushant on 1/1/2017.
 */
public class Insert {
    public boolean checkInsertIntoQuery(String query) throws Exception{

        if(!query.contains("insert"))
            return false;

        String queryFirstPart = query.substring(0, query.indexOf('(')).toLowerCase();
        System.out.println("sdf"+queryFirstPart);

        if(queryFirstPart.matches("^insert into \\w+ values")){
            String queryValuesPart = query.substring(query.indexOf('(') + 1, query.lastIndexOf(')'));
            System.out.println("test"+queryValuesPart);
            //  System.out.println(queryValuesPart);

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
            System.out.println("fe"+queryVariablesPart);
            System.out.println(queryValuesPart);
            if (queryVariablesPart.isEmpty())
            {
                System.err.println("variables are empty");
            }
            if (queryValuesPart.isEmpty())
            {
                System.err.println("values are empty");
            }

//          for variables part
            if(queryVariablesPart.contains(",") && queryValuesPart.contains(",")){

                String variables[] = queryVariablesPart.split(",");
                String values[] = queryValuesPart.split(",");

                if(variables.length != values.length)
                {
                    System.err.println("variables or values are missing");
                }

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
                // System.out.println(queryFirstPart);
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
