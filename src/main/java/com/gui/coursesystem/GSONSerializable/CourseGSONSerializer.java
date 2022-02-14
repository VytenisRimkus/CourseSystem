package com.gui.coursesystem.GSONSerializable;

import com.google.gson.*;
import com.gui.coursesystem.ds.Course;
import com.gui.coursesystem.ds.User;

import java.lang.reflect.Type;

public class CourseGSONSerializer implements JsonSerializer<Course> {
    @Override
    public JsonElement serialize(
            Course course, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject crsJson = new JsonObject();
        JsonArray userJson = new JsonArray();
        crsJson.addProperty("ID", course.getId());
        crsJson.addProperty("name", course.getCourseName());
        crsJson.addProperty("description", course.getCourseDescription());
        crsJson.addProperty("startDate", course.getStartDate().toString());
        crsJson.addProperty("endDate", course.getEndDate().toString());

        GsonBuilder gson = new GsonBuilder();

        gson.registerTypeAdapter(User.class, new ResponsibleUserGSONSerializer());
        Gson parser = gson.create();

        for (User u: course.getResponsibleUsers()) {
            userJson.add(parser.toJsonTree(u));
        }

        crsJson.add("responsibleUsers",userJson);

        return crsJson;
    }
}
