package davydoff.controllers;

import davydoff.models.Application;
import davydoff.models.MyUser;
import davydoff.services.AppService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/apps")
@AllArgsConstructor
public class AppController {
    private AppService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to unprotected page";
    }

    @GetMapping("/all-app")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> allApplications() {
        return service.allApplications();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Application applicationById(@PathVariable int id) {
        return service.applicationById(id);
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody MyUser user) {
        service.addUser(user);
        return "User is saved";
    }
}
