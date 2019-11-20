package pl.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import pl.spring.models.AppUser;
import pl.spring.service.AppUserRepository;
import pl.spring.service.AsyncService;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("api")
public class AuthenticationRestController {

    private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private AsyncService asyncService;

    @GetMapping(path = "/basicauth")
    public ResponseEntity<String> basicauth() {
        return ResponseEntity.ok("Jesteś zalogowany");
    }

    @GetMapping(value = "/getAllUsers", produces = "application/json")
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/findByLogin-{login}", produces = "application/json")
    public AppUser findByLogin(@PathVariable String login) {
        return userRepository.findByLogin(login);
    }

    @PostMapping(path = "/createUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createUser(@RequestBody AppUser user) {
        try {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Dodano użytkownika");
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Zostałeś wylogowany");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/async")
    @ResponseBody
    public Object standardProcessing() throws Exception {
        log.info("Outside the @Async logic - before the async call: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        asyncService.asyncCall();
        log.info("Inside the @Async logic - after the async call: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/async2")
    @ResponseBody
    public Callable<Boolean> springMVCAsyncTest() {
        return asyncService.checkIfPrincipalPropagated();
    }

}
