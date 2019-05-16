package pl.edu.pw.mini.auth.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.auth.rest.user.service.UsersService;
import pl.edu.pw.mini.core.security.authorization.AllowAll;
import pl.edu.pw.mini.core.tools.StringWrapper;
import pl.gutowskid.auth.api.AddUserDto;
import pl.gutowskid.auth.api.LoginDto;

import javax.servlet.http.HttpServletRequest;


@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @AllowAll
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST, produces = "application/json")
    public StringWrapper login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        return usersService.login(loginDto, request);
    }

    @AllowAll
    @RequestMapping(value = "/auth/adduser", method = RequestMethod.POST, produces = "application/json")
    public StringWrapper addUser(@RequestBody AddUserDto addUserDto) {
        return usersService.addUser(addUserDto);
    }
}
