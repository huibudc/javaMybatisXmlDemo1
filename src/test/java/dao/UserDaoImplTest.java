package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserDaoImplTest {
    //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
    //构建sqlSession的工厂
    private SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(UserDaoImplTest.class.getClassLoader().getResourceAsStream("config.xml"));
    //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
    //Reader reader = Resources.getResourceAsReader(resource);
    //构建sqlSession的工厂
    //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    //创建能执行映射文件中sql的sqlSession
    private SqlSession session;
    /**
     * 映射sql的标识字符串，
     * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
     * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
     */
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        session = sessionFactory.openSession();
        if (userDao == null) {
            userDao = new UserDaoImpl(session);
        }
        userDao.clearTable();
        userDao.add(new User("test1","test11"));
        userDao.add(new User("test2","test22"));
    }

    @After
    public void tearDown() throws Exception {
        userDao.clearTable();
        if (session != null) {
            session.close();
        }
    }

    @org.junit.Test
    public void getAll() throws Exception {
        List<User> users = userDao.getAll();
        assertThat(users.size(), is(2));
    }

    @org.junit.Test
    public void getById() throws Exception {
    }

    @org.junit.Test
    public void deleteById() throws Exception {
    }

    @Test
    public void insertUser() throws Exception {
        User user = new User("insert","insert123");
        List<User> beforeDelete = userDao.getAll();
        assertThat(beforeDelete.size(), is(2));
        userDao.add(user);
        List<User> users = userDao.getAll();
        assertThat(users.size(), is(3));
        userDao.deleteByName(user.getName());
        List<User> afterDelete = userDao.getAll();
        assertThat(afterDelete.size(), is(2));
    }
}