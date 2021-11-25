package apap.tugas.siretail.service;

import apap.tugas.siretail.model.UserModel;
import apap.tugas.siretail.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;

    @Override
    public List<UserModel> getListUser() {
        return userDb.findAll();
    }

    @Override
    public UserModel updateUserByUsername(String username) {
        return null;
    }
}
