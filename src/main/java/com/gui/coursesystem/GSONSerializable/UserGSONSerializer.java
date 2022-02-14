package com.gui.coursesystem.GSONSerializable;

import com.google.gson.*;
import com.gui.coursesystem.ds.Course;
import com.gui.coursesystem.ds.User;

import java.lang.reflect.Type;

public class UserGSONSerializer implements JsonSerializer<User> {
    @Override
    public JsonElement serialize(
            User user, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject userJson = new JsonObject();
        JsonArray crsJson = new JsonArray();
        userJson.addProperty("ID", user.getId());
        userJson.addProperty("name", user.getName());
        userJson.addProperty("surname", user.getLastName());
        userJson.addProperty("login", user.getLogin());
        userJson.addProperty("password", user.getPassword());
        userJson.addProperty("email", user.getEmail());
        userJson.addProperty("position", user.getPosition());
        userJson.addProperty("type", user.getUserType().toString());
//        userJson.addProperty("created_date", user.getDateCreated().toString());
//        userJson.addProperty("modified_date", user.getDateModified().toString());

        GsonBuilder gson = new GsonBuilder();
        Gson parser = gson.create();

        for (Course c: user.getMyCourses()) {
            crsJson.add(parser.toJsonTree(c));
        }
        userJson.add("responsibleCourses",crsJson);

        return userJson;
    }
}
