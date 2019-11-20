package pl.spring.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import pl.spring.models.AppUser;


@Service
public interface AppUserRepository extends MongoRepository<AppUser, String> {
    public AppUser findByLogin(String login);
}
