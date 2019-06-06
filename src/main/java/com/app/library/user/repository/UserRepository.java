package com.app.library.user.repository;

import com.app.library.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    Optional<User> findUserById(Long userId);

    @Override
    User save(User user);

    @Override
    void delete(Long userId);
}
