package com.gui.coursesystem.webControllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gui.coursesystem.GSONSerializable.*;
import com.gui.coursesystem.ds.Course;
import com.gui.coursesystem.ds.User;
import com.gui.coursesystem.hibernate.UserHibController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@Controller
public class WebUserController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    UserHibController userHibernateControl = new UserHibController(entityManagerFactory);

    @RequestMapping(value = "/user/userList", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllUsers() {

        List<User> allUsers = userHibernateControl.getUserList();

        GsonBuilder gson = new GsonBuilder();
//        gson.registerTypeAdapter(User.class, new UserGSONSerializer()).registerTypeAdapter(LocalDate.class, new LocalDateGSONSerializer());
//        Gson parser = gson.create();
//        parser.toJson(allUsers.get(0));

        Type userList = new TypeToken<List<User>>() {
        }.getType();
        gson.registerTypeAdapter(User.class, new UserGSONSerializer()).registerTypeAdapter(userList, new AllUserGSONSerializer()).registerTypeAdapter(LocalDate.class, new LocalDateGSONSerializer());
        Gson parser = gson.create();

        return parser.toJson(allUsers);
    }

    @RequestMapping(value = "/user/userInfo", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getUserInfoById(@RequestParam("id") String id) {


        User user = userHibernateControl.findUser(Integer.parseInt(id));
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(User.class, new UserGSONSerializer()).registerTypeAdapter(LocalDate.class, new LocalDateGSONSerializer());
        Gson parser = gson.create();

        return "User ID " + user.getName();
        //return parser.toJson(user);
    }

    @RequestMapping(value = "/user/userLogin", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String loginUser(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String loginName = data.getProperty("login");
        String password = data.getProperty("psw");
        User user = userHibernateControl.findUserLogin(loginName, password);

        if (user == null) {
            return "Wrong credentials";
        }
        return String.valueOf(user.getId());
    }
}
