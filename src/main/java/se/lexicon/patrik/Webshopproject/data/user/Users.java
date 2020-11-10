package se.lexicon.patrik.Webshopproject.data.user;

import se.lexicon.patrik.Webshopproject.model.User;

import java.util.Collection;
import java.util.Optional;

public interface Users {
    User create (User user);
    Collection<User> findAll();
    Optional<User> findById(int userId);
    Collection<User> findByName(String name);
    User update(User user);
    boolean deleteById(int personId);
}
