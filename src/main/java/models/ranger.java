package models;

import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class ranger {
    public String name;
    public int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ranger ranger = (ranger) o;
        return id == ranger.id &&
                Objects.equals(name, ranger.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public static List<ranger> all() {
        String sql = "SELECT * FROM rangers";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(ranger.class);
        }
    }

    public String getName() {
        return name ;
    }

    public int getId() {
        return id;
    }

}
