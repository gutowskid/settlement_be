package pl.edu.pw.mini.auth.rest.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.mini.core.model.user.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
}
