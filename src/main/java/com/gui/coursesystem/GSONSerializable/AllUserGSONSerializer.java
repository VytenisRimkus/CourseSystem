package com.gui.coursesystem.GSONSerializable;

import com.google.gson.*;
import com.gui.coursesystem.ds.User;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class AllUserGSONSerializer implements JsonSerializer<List<User>> {
    @Override
    public JsonElement serialize(List<User> users, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new UserGSONSerializer()).registerTypeAdapter(LocalDate.class, new LocalDateGSONSerializer());
        Gson parser = gsonBuilder.create();

        for (User u: users) {
            jsonArray.add(parser.toJson(u));
        }
        //System.out.println(jsonArray);
        return jsonArray;
    }
}
