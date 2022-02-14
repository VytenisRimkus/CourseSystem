package com.gui.coursesystem.GSONSerializable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.gui.coursesystem.ds.User;

import java.lang.reflect.Type;

public class ResponsibleUserGSONSerializer implements JsonSerializer<User> {
    @Override
    public JsonElement serialize(
            User user, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject userJson = new JsonObject();

        userJson.addProperty("ID", user.getId());
        userJson.addProperty("name", user.getName());
        userJson.addProperty("surname", user.getLastName());
//        userJson.addProperty("login", user.getLogin());
//        userJson.addProperty("password", user.getPassword());
        userJson.addProperty("email", user.getEmail());
//        userJson.addProperty("position", user.getPosition());
//        userJson.addProperty("type", user.getUserType().toString());

        return userJson;
    }
}
