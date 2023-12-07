package models;

import org.sql2o.*;
import java.util.List;
import java.util.Objects;
import java.sql.Timestamp;
public class Sighting {

    private String location;
    private String name;
    private int animalId;
    private int id;
    private String type;
    private String age;
    private String health;
    public Timestamp time_sighted;




    public Sighting(String name, String location, int animalId,String type){
        this.location = location;
        this.name = name;
        this.animalId = animalId;
        this.type = type;
    }

    public Timestamp getTime_sighted() {
        return time_sighted;
    }

    public Sighting(String name, String location, int animalId, String type, String age, String health){
        this.location = location;
        this.name = name;
        this.animalId = animalId;
        this.type = type;
        this.age = age;
        this.health = health;
    }

    @Override
    public boolean equals(Object otherSighting) {
        if (this == otherSighting) return true;
        if (otherSighting == null || getClass() != otherSighting.getClass()) return false;
        Sighting sighting = (Sighting) otherSighting;
        return Objects.equals(location, sighting.location) &&
                Objects.equals(name, sighting.name);
    }

    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (name,location,animalid,type,time_sighted,health,age) VALUES (:name,:location,:animalId,:type,now(),:health,:age)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("location",this.location)
                    .addParameter("animalId",animalId)
                    .addParameter("type",type)
                    .addParameter("age",age)
                    .addParameter("health",health)
                    .executeUpdate()
                    .getKey();


        }
    }
    public static List<Sighting> all(){
        String sql = "SELECT * FROM sightings ORDER BY id ASC";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Sighting.class);

        }
    }

    public static Sighting find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sightings where id = :id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, name);
    }

    public String getRangerName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public int getAnimalId() {
        return animalId;
    }
}
