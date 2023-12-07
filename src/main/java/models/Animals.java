package models;

import java.util.List;
import java.util.Objects;
import org.sql2o.*;
import java.sql.Timestamp;

public class Animals {

    public String name;
    public int id;
    public String type;


    public static final String ANIMAL_TYPE = "Common";


    public Animals(String name){
        if(name.equals("")){
            throw new IllegalArgumentException("Please enter an animal name.");
        }
        this.name = name;

        this.type = ANIMAL_TYPE;


    }


    @Override
    public boolean equals(Object otherAnimal) {
        if (this == otherAnimal) return true;
        if (otherAnimal == null || getClass() != otherAnimal.getClass()) return false;
        Animals animals = (Animals) otherAnimal;
        return this.getName().equals(animals.getName());


    }



    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type) VALUES (:name,:type)";
             this.id = (int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                     .addParameter("type",this.type)
                     .throwOnMappingFailure(false)
                    .executeUpdate()
                     .getKey();


        }
    }
    public static List<Animals> all() {
        String sql = "SELECT * FROM animals ORDER BY id ASC";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Animals.class);
        }
    }

    public static Animals find(int id){
        String sql = "SELECT * FROM animals WHERE id = :id";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Animals.class);

        }
    }

    public void update(){
        String sql = "UPDATE animals SET name = :name WHERE id = :id";

        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name",name)
                    .addParameter("id",id)
                    .executeUpdate();

        }
    }







    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }



    public String getType() {
        return type;
    }



}
