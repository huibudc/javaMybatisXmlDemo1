package dao;

import pojo.User;

public interface UserDao extends BaseDao<User>{
    void deleteByName(String name);
}
