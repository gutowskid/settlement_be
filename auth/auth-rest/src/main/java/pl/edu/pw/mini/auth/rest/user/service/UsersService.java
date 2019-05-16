package pl.edu.pw.mini.auth.rest.user.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.auth.rest.common.ErrorCode;
import pl.edu.pw.mini.auth.rest.user.domain.Users;
import pl.edu.pw.mini.auth.rest.user.domain.UsersRepository;
import pl.edu.pw.mini.core.security.authentication.TokenHandler;
import pl.edu.pw.mini.core.tools.StringWrapper;
import pl.gutowskid.auth.api.AddUserDto;
import pl.gutowskid.auth.api.LoginDto;

import javax.servlet.http.HttpServletRequest;


@Service
public class UsersService {

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private AddUserAssembler addUserAssembler;

    public StringWrapper login(LoginDto loginDto, HttpServletRequest request) {
        Users user = userRepository.findById(loginDto.getLogin()).orElseThrow(() -> ErrorCode.AUT_0001);
        String passwordHash = calculateHash(loginDto.getPassword());

        if(!user.getPasswordHash().equals(passwordHash)) {
            throw ErrorCode.AUT_0002;
        }
        return StringWrapper.fromValue(tokenHandler.getTokenWithUser(user.getId(), user.getRole(), user.getForename(), user.getSurname(), request));
    }
    private String calculateHash(String password) {
        return DigestUtils.sha256Hex(password);
    }

    public StringWrapper addUser(AddUserDto addUserDto) {
        Users users = addUserAssembler.toEntity(addUserDto);
        String password = RandomStringUtils.randomAlphanumeric(8);
        users.setPasswordHash(calculateHash(password));
        users.setIsActive(true);
        userRepository.save(users);
        return StringWrapper.fromValue(password);
    }

    public void disableUser(String userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> ErrorCode.AUT_0003);
        user.setIsActive(false);
        userRepository.save(user);
    }
}
