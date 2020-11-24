package se.lexicon.patrik.Webshopproject.data.user;

import se.lexicon.patrik.Webshopproject.model.WebUser;

import java.util.Collection;
import java.util.Optional;

public interface Users {
    WebUser create (WebUser user);
    Collection<WebUser> findAll();
    Optional<WebUser> findById(int userId);
    Collection<WebUser> findByName(String name);
    WebUser update(WebUser user);
    boolean deleteById(int personId);
}
