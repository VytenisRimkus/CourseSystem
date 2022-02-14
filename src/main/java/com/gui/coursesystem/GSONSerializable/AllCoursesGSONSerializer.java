package com.gui.coursesystem.GSONSerializable;

import com.google.gson.*;
import com.gui.coursesystem.ds.Course;

import java.lang.reflect.Type;
import java.util.List;

public class AllCoursesGSONSerializer implements JsonSerializer<List<Course>> {
    @Override
    public JsonElement serialize(List<Course> courses, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Course.class, new CourseGSONSerializer());
        Gson parser = gsonBuilder.create();

        for (Course c : courses) {
            jsonArray.add(parser.toJson(c));
        }
        //System.out.println(jsonArray);
        return jsonArray;
    }
}