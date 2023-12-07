package models;
import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class location {
    private String name;
    private int id;

    public  location(int id,String name){
        this.id = id;
        this.name = name;

    }



    public static List <location> all() {
        String sql = "SELECT * FROM locations  ORDER BY id ASC";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(location.class);
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
