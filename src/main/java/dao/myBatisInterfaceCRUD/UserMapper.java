package dao.myBatisInterfaceCRUD;

import pojo.User;

import java.util.List;

public interface UserMapper {
    User getUser(int id);

    List<User> getAll();

    void insertUser(User user);

    void deleteByName(String name);

    void clearTable();
}
