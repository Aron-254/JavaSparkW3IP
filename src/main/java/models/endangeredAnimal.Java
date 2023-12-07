package models;

import java.util.List;
import java.util.Objects;
import org.sql2o.*;
import java.sql.Timestamp;

public class endangeredAnimal extends Animals {
    private String health;
    private String age;
    private String type;

    public static final String ANIMAL_TYPE = "Endangered";



    public endangeredAnimal(String name, String health, String age){
        super(name);
        if(name.equals("")|| health.equals("")|| age.equals("") ){
            throw new IllegalArgumentException("Please input all fields as required");
        }
        this.health = health;
        this.age = age;
        type = ANIMAL_TYPE;
    }



    @Override
    public boolean equals(Object otherAnimal) {
        if (this == otherAnimal) return true;
        if (otherAnimal == null || getClass() != otherAnimal.getClass()) return false;
        endangeredAnimal animal = (endangeredAnimal) otherAnimal;
        return (this.name.equals(animal.name));
    }





    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangered_animals (name,type,health,age) VALUES (:name,:type,:health,:age)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("type",this.type)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();


        }
    }

    public static List<endangeredAnimal> getAll() {
        String sql = "SELECT * FROM endangered_animals ORDER BY id ASC";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(endangeredAnimal.class);
        }
    }


    public String getAge() {
        return age;
    }

    public String getHealth() {
        return health;
    }

//    public String getName() {
//        return name;
//    }
}
