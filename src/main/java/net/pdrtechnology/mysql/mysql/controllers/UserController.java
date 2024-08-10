package net.pdrtechnology.mysql.mysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import java.util.List;

import net.pdrtechnology.mysql.mysql.models.UserDto;
import net.pdrtechnology.mysql.mysql.models.Users;
import net.pdrtechnology.mysql.mysql.services.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repos;

    @GetMapping({"","/"})
    public String showUserList(Model model){
        List<Users> users = repos.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        UserDto userDto = new UserDto();  
        model.addAttribute("userDto", userDto);
        return "users/CreateUser";
    }

    @PostMapping("/create")
    public String createUser(
        @Valid
        @ModelAttribute UserDto userdto,
        BindingResult result
    ) {
        if (result.hasErrors()){
            System.out.println(result.toString());
            return "users/CreateUser";
        }

        Users users = new Users();
        users.setName(userdto.getName());
        users.setEmail(userdto.getEmail());
        users.setDescription(userdto.getDescription());
        repos.save(users);
        return "redirect:/users";
    }


    @GetMapping("/edit")
    public String showEditPage(
        Model model,
        @RequestParam int id 
    ){

        try {
            Users user = repos.findById(id).get();
            model.addAttribute("user", user);

            UserDto userDto = new UserDto();
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setDescription(user.getDescription());

            model.addAttribute("userDto", userDto);
            
        } catch (Exception ex) {
            System.out.println("Exception : "+ex.getMessage());
        }

        return "users/EditUser";
    }

    @PostMapping("/edit")
    public String editUser(
        Model model,
        @RequestParam int id,
        @Valid @ModelAttribute UserDto userdto,
        BindingResult result
    ) {
        try {
            Users user = repos.findById(id).get();
            model.addAttribute("user", user);

            if (result.hasErrors()){
                return "users/EditUser";
            }

            user.setName(userdto.getName());
            user.setEmail(userdto.getEmail());
            user.setDescription(userdto.getDescription());
            repos.save(user);

        } catch (Exception e) {
            System.out.println("Exception : "+e.getMessage());
        }
        
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteProduct(
        @RequestParam int id
    ){
        try {
            Users user = repos.findById(id).get();

            repos.delete(user);
        } catch (Exception e) {
            System.out.println("Exception : "+e.getMessage());
       
        }

        return "redirect:/users";
    }
    
}
