package com.gui.coursesystem.webControllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gui.coursesystem.GSONSerializable.AllCoursesGSONSerializer;
import com.gui.coursesystem.GSONSerializable.CourseGSONSerializer;
import com.gui.coursesystem.GSONSerializable.LocalDateGSONSerializer;
import com.gui.coursesystem.ds.Course;
import com.gui.coursesystem.hibernate.CourseHibController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@Controller
public class WebCourseController {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    CourseHibController courseHibController = new CourseHibController(entityManagerFactory);

    @RequestMapping(value = "/course/crsList")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllCourses() {

        List<Course> allCrs = courseHibController.getCourseList();

//        GsonBuilder gson = new GsonBuilder();
//        gson.registerTypeAdapter(Course.class, new CourseGSONSerializer());
//        Gson parser = gson.create();
//        parser.toJson(allCrs.get(0));
//
//        Type courseList = new TypeToken<List<Course>>() {
//        }.getType();
//        gson.registerTypeAdapter(courseList, new AllCoursesGSONSerializer());
//        parser = gson.create();
        GsonBuilder gson = new GsonBuilder();
        Type coursesList = new TypeToken<List<Course>>(){
        }.getType();
        gson.registerTypeAdapter(Course.class, new CourseGSONSerializer()).registerTypeAdapter(LocalDate.class, new LocalDateGSONSerializer()).registerTypeAdapter(coursesList, new AllCoursesGSONSerializer());
        Gson parser = gson.create();
        return parser.toJson(allCrs);
    }

    @RequestMapping(value = "/course/crsInfo", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getCourseInfoById(@RequestParam("id") int id) {

        Course course = courseHibController.findCourse(id);
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Course.class, new CourseGSONSerializer());
        Gson parser = gson.create();
        return parser.toJson(course);
    }
}
