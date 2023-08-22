package mkcrud.mkboard.service;

import mkcrud.mkboard.domain.entity.BoardUser;
import mkcrud.mkboard.domain.entity.JpaBoardUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JpaBoardUserRepository jpaBoardUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(JpaBoardUserRepository jpaBoardUserRepository, PasswordEncoder passwordEncoder) {
        this.jpaBoardUserRepository = jpaBoardUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public BoardUser createUser(String username, String email, String password) {
        BoardUser user = new BoardUser();
        user.setUsername(username);
        user.setEmail(email);
        //패스워드 인코딩 (BCrypt 해싱 함수를 사용해서 암호화)
        user.setPassword(passwordEncoder.encode(password));
        jpaBoardUserRepository.save(user);

        return user;
    }
}
