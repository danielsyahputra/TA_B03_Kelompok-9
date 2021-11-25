package apap.tugas.siretail.service;

import apap.tugas.siretail.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
}
