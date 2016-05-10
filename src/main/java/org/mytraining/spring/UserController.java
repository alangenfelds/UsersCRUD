package org.mytraining.spring;

import org.mytraining.model.User;
import org.mytraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listPersons(HttpServletRequest request, Model model) {

        // pagination ?? --------------

        PagedListHolder pagedListHolder = new PagedListHolder(this.userService.listUsers());
        int page = ServletRequestUtils.getIntParameter(request,"p",0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(10);
        model.addAttribute("pagedListHolder",pagedListHolder);

        //---------------


        model.addAttribute("user", new User());
        //model.addAttribute("listUsers", this.userService.listUsers());
        return "users";
    }


    //For add and update person both
    @RequestMapping(value= "/users/add/{page}", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, @PathVariable ("page") int currentPage, HttpServletRequest request,Model model){

        if(user.getId() == 0){
            //new person, add it
            this.userService.addUser(user);
        }else{
            //existing person, call update
            this.userService.updateUser(user);
        }

        PagedListHolder pagedListHolder = new PagedListHolder(this.userService.listUsers());
        int page = ServletRequestUtils.getIntParameter(request,"p", currentPage);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(10);
        model.addAttribute("user", new User());
        model.addAttribute("pagedListHolder",pagedListHolder);
        return "users";

       // return "redirect:/users";
    }


    @RequestMapping("/remove/{id}/{page}")
    public String removeUser(@PathVariable("id") int id,@PathVariable("page") int currentPage, HttpServletRequest request, Model model){

        this.userService.removeUser(id);

        PagedListHolder pagedListHolder = new PagedListHolder(this.userService.listUsers());
        int page = ServletRequestUtils.getIntParameter(request,"p", currentPage);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(10);
        model.addAttribute("user", new User());
        model.addAttribute("pagedListHolder",pagedListHolder);
        return "users";
        // return "redirect:/users";
    }

    @RequestMapping("/edit/{id}/{page}")
    public String editUser(@PathVariable("id") int id, @PathVariable("page") int currentPage, HttpServletRequest request, Model model){

        PagedListHolder pagedListHolder = new PagedListHolder(this.userService.listUsers());
        int page = ServletRequestUtils.getIntParameter(request,"p", currentPage);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(10);
        model.addAttribute("pagedListHolder",pagedListHolder);

        model.addAttribute("user", this.userService.getUserById(id));
        //model.addAttribute("listUsers", this.userService.listUsers());

        return "users";
    }


    @RequestMapping(value= "/find/{page}", method = RequestMethod.POST)
    public String findByName(@PathVariable ("page") int currentPage, @RequestParam("username") String username, HttpServletRequest request, Model model){

        PagedListHolder pagedListHolder = new PagedListHolder(this.userService.listUsersByName(username));
        int page = ServletRequestUtils.getIntParameter(request,"p", currentPage);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(10);
        model.addAttribute("user", new User());
        model.addAttribute("pagedListHolder",pagedListHolder);
        return "users";

        // return "redirect:/users";
    }

}
