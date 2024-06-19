package org.example.storespring.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpSession;
import org.example.storespring.entity.User;
import org.example.storespring.model.Mail;
import org.example.storespring.service.CloudinaryService;
import org.example.storespring.service.MailService;
import org.example.storespring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

public class UserController {
    UserService userService;
    MailService mailService;
    HttpSession session;
    CloudinaryService cloudinaryService;

@GetMapping(path = "/login")
public ResponseEntity<User> Login (String id, String password){
    User user = userService.findByIdAndRole(id, "user");
        if (user == null && user.getPassword() != null){
            //Ma hoa
            String decodedValue = new String(Base64.getDecoder().decode(user.getPassword()));
            if (password.equals(decodedValue)){
                user.setPassword(decodedValue);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            else{
                return null;
            }
        }
        else return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping(path = "/signup", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<User> SignUp(String username, String fullname, String email, String password) {
        User user = userService.findByIdAndRole(username, "user");
        if (user != null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            String encodedValue = Base64.getEncoder().encodeToString(password.getBytes());
            String avatar = "https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png";
            User newUser = userService.saveUser(new User(username, "default", "user", encodedValue, fullname, avatar,
                    email, null, null, null, null));
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
    }
    @PostMapping(path = "/forgot", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> Forgot(String id) {
        User user = userService.findByIdAndRole(id, "user");
        if (user != null) {
            int code = (int) Math.floor(((Math.random() * 899999) + 100000));
            Mail mail = new Mail();
            mail.setMailFrom("");
            mail.setMailTo(user.getEmail());
            mail.setMailSubject("For got Password");
            mail.setMailContent("Your code is: " + code);
            mailService.sendEmail(mail);
            session.setAttribute("code", code);
            return new ResponseEntity<String>(new Gson().toJson(String.valueOf(code)), HttpStatus.OK);
        } else
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
    @PostMapping(path = "/forgotnewpass", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> ForgotNewPass(String id, String code, String password){
        User user = userService.findByIdAndRole(id, "user");
        if (user != null) {
            String encodedValue = Base64.getEncoder().encodeToString(password.getBytes());
            user.setPassword(encodedValue);
            userService.saveUser(user);
            return new ResponseEntity<String>(password, HttpStatus.OK);
        } else
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping(path = "changepassword", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> ChangePassword(String id, String password) {
        User user = userService.findByIdAndRole(id, "user");
        if (user != null) {
            String encodedValue = Base64.getEncoder().encodeToString(password.getBytes());
            user.setPassword(encodedValue);
            userService.saveUser(user);
            return new ResponseEntity<String>(password, HttpStatus.OK);
        } else
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping(path = "update", consumes = "multipart/form-data")
    public ResponseEntity<User> UpdateAvatar(String id, MultipartFile avatar, String fullname, String email,String phoneNumber, String address){
        User user = userService.findByIdAndRole(id, "user");
        if (user!= null) {
            if (avatar!= null) {
               String url = cloudinaryService.uploadFile(avatar);
            }
            user.setUser_Name(fullname);
            user.setEmail(email);
            user.setPhone_Number(phoneNumber);
            user.setAddress(address);
            userService.saveUser(user);
            if(user.getPassword()!=null)
                user.setPassword(new String(Base64.getDecoder().decode(user.getPassword())));
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else
            return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
}
//    @PostMapping(path = "google", consumes = "application/x-www-form-urlencoded")
//    public ResponseEntity<User> LoginWithGoogle(String id, String fullname, String email, String avatar) {
//        User user = userService.findByIdAndRole(id, "user");
//        if (user == null) {
//            user = userService
//                    .saveUser(new User(id, "google", "user", null, fullname, avatar, email, null, null, null, null));
//        }
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }

}


