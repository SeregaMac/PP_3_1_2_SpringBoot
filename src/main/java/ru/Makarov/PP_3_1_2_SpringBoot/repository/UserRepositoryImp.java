package ru.Makarov.PP_3_1_2_SpringBoot.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.Makarov.PP_3_1_2_SpringBoot.models.User;

import java.util.List;


@Repository
public class UserRepositoryImp implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
