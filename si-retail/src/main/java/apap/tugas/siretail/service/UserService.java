package apap.tugas.siretail.service;

import apap.tugas.siretail.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> getListUser();

    UserModel findUserbyUsername(String username);
}
