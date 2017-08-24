package dao;

import org.apache.ibatis.session.SqlSession;
import pojo.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private final SqlSession session;

    public UserDaoImpl(SqlSession session) {
        this.session = session;
    }

    public List<User> getAll() {
        return session.selectList("mappings.userMapping.getAll");
    }

    public User getById(int id) {
        return session.selectOne("mappings.userMapping.getUser", id);
    }

    public void deleteById(int id) {

    }
    public void add(User user) {
        session.insert("mappings.userMapping.insertUser", user);
        session.commit();
    }

    public void clearTable() {
        session.delete("mappings.userMapping.clearTable");
        session.commit();
    }

    public void deleteByName(String name) {
        session.delete("mappings.userMapping.deleteByName", name);
        session.commit();
    }
}
