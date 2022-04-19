package gs.gs.controller;

import gs.gs.model.User;
import gs.gs.payload.request.LoginForm;
import gs.gs.payload.request.SignUpForm;
import gs.gs.payload.response.Response;
import gs.gs.repositories.UserRepo;
import gs.gs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@Valid @RequestBody SignUpForm signUpForm){
        // Response object
        Response response = new Response();

        // Check the email and username in the signupForm
        if(userRepo.existsByUsername(signUpForm.getUsername())){
            response.setMessage("Error: Username is already taken");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if(userRepo.existsByEmail(signUpForm.getEmail())){
            response.setMessage("Error: Email is already taken");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        //Create a new user
        User user = new User(
                signUpForm.getUsername(),
                encoder.encode(signUpForm.getPassword()),
                signUpForm.getFirstName(),
                signUpForm.getLastName(),
                signUpForm.getEmail()
        );
        userRepo.save(user);
        response.setMessage("user Registered successfuly");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginForm loginForm){
        // Response object
        Response response = new Response();

        Optional<User> user = userRepo.findByUsername(loginForm.getUsername());
        if (user.isPresent()){
            if (encoder.matches(loginForm.getPassword(),user.get().getPassword())){
                return new ResponseEntity(response, HttpStatus.OK);
            }else{
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
}
