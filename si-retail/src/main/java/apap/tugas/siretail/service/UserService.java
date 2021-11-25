package apap.tugas.siretail.service;

import apap.tugas.siretail.model.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> getListUser();
    UserModel updateUserByUsername(String username);
}
