package ru.Makarov.PP_3_1_2_SpringBoot.service;

import ru.Makarov.PP_3_1_2_SpringBoot.models.User;

import java.util.List;


public interface UserService {
    List<User> getUsers();

    void save(User user);

    User getUser(long id);

    void deleteUser(long id);
}
