package dao.myBatisInterfaceCRUD;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserMapperTest {
    //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
    //构建sqlSession的工厂
    private SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(UserMapperTest.class.getClassLoader().getResourceAsStream("config.xml"));
    //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
    //Reader reader = Resources.getResourceAsReader(resource);
    //构建sqlSession的工厂
    //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    //创建能执行映射文件中sql的sqlSession
    private SqlSession session;
    private UserMapper userMapper;

    /**
     * 映射sql的标识字符串，
     * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
     * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
     */
    @Before
    public void setUp() throws Exception {
        session = sessionFactory.openSession(); //not thread safe
        userMapper = session.getMapper(UserMapper.class);
        userMapper.insertUser(new User("test", "11"));
        userMapper.insertUser(new User("test1", "22"));
    }

    @After
    public void tearDown() throws Exception {
        userMapper.clearTable();
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = userMapper.getAll();
        System.out.println(all.toString());
        assertThat(all.size(), is(2));
    }

    @Test
    public void delete() throws Exception {
        List<User> before = userMapper.getAll();
        assertThat(before.size(), is(2));
        userMapper.deleteByName("test");
        List<User> after = userMapper.getAll();
        assertThat(after.size(), is(1));
    }
}