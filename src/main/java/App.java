import models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        // This tells our app that if Heroku sets a port for us, we need to use that port.
        // Otherwise, if they do not, continue using port 4567.

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);
        get("/",(req,res)->{
            Map<String,Object>model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get("/new/sighting",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            model.put("locations", location.all());
            model.put("animals", Animals.all());
            model.put("rangers",ranger.all());
            return new ModelAndView(model,"sightingForm.hbs");
        },new HandlebarsTemplateEngine());

        post("/new/sighting",(req,res)->{
            Map<String,Object>model = new HashMap<>();
            String rangerName = req.queryParams("rangerName");
            String location = req.queryParams("locationName");
            int animalId = Integer.parseInt(req.queryParams("animalId"));
            String type = Animals.find(animalId).getType();
            Sighting newSighting =  new Sighting(rangerName,location,animalId,type);
            newSighting.save();
            res.redirect("/sightings");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/new/endangered",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            model.put("locations", location.all());
            model.put("enAnimals", endangeredAnimal.getAll());
            model.put("rangers",ranger.all());
            return new ModelAndView(model,"endangeredSighting.hbs");
        },new HandlebarsTemplateEngine());

        post("/new/endangered",(req,res)->{
            Map<String,Object>model = new HashMap<>();
            String rangerName = req.queryParams("rangerName");
            String location = req.queryParams("locationName");
            int animalId = Integer.parseInt(req.queryParams("animalId"));
            String type = endangeredAnimal.find(animalId).getType();
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            Sighting newSighting =  new Sighting(rangerName,location,animalId,type,health,age);
            newSighting.save();
            res.redirect("/animals");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/new/animal",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"animalForm.hbs");
        },new HandlebarsTemplateEngine());

        post("/new/animal",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            String animalName = req.queryParams("animalName");
            Animals animal = new Animals(animalName);
            animal.save();
            res.redirect("/animals");
            return null;

        },new HandlebarsTemplateEngine());


        get("/new/endangeredAnimal",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"endangeredForm.hbs");
        },new HandlebarsTemplateEngine());

        post("/new/endangeredAnimal",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            String animalName = req.queryParams("animalName");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            endangeredAnimal animal = new endangeredAnimal(animalName,health,age);
            animal.save();
            res.redirect("/endangeredAnimals");
            return null;

        },new HandlebarsTemplateEngine());




        get("/rangers",(req,res)->{
            Map<String,Object>model = new HashMap<>();
            model.put("rangers",ranger.all());
            return new ModelAndView(model,"rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/locations",(req,res)->{
            Map<String,Object>model = new HashMap<>();
            model.put("locations",location.all());
            return new ModelAndView(model,"locationList.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals",(req,res)->{
            Map<String,Object>model = new HashMap<>();
            model.put("animals",Animals.all());
            return new ModelAndView(model,"animalDetail.hbs");
        }, new HandlebarsTemplateEngine());


        get("/endangeredAnimals",(req,res)->{
            Map<String,Object>model = new HashMap<>();
            model.put("animals",endangeredAnimal.getAll());
            return new ModelAndView(model,"endangeredDetails.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings",(req,res)->{
            Map<String,Object>model = new HashMap<>();
            model.put("sightings",Sighting.all());
            return new ModelAndView(model,"sightingDetails.hbs");
        }, new HandlebarsTemplateEngine());


    }


}
