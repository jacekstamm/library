package com.app.user.repository;

import com.app.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    Optional<User> findUserById(Long id);

    @Override
    User save(User user);

    @Override
    void delete(Long id);

    void deleteAll();
}
