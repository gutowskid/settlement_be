package pl.edu.pw.mini.auth.rest.user.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.auth.rest.user.domain.Users;
import pl.gutowskid.auth.api.AddUserDto;

@Component
public class AddUserAssembler {
    public Users toEntity(AddUserDto dto) {
        Users entity = new Users();
        entity.setId(dto.getUserId());
        entity.setRole(dto.getRole());
        entity.setForename(dto.getForename());
        entity.setSurname(dto.getSurname());
        return entity;
    }
}
