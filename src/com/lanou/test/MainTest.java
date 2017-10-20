package com.lanou.test;

import com.lanou.dao.DepartmentDao;
import com.lanou.dao.PostDao;
import com.lanou.dao.StaffDao;
import com.lanou.dao.impl.DepartmentDaoImpl;
import com.lanou.dao.impl.PostDaoImpl;
import com.lanou.dao.impl.StaffDaoImpl;
import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;

import com.lanou.service.DepartmentService;
import com.lanou.service.impl.DepartmentServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/20.
 */
public class MainTest {


    private SessionFactory sessionFactory;

    @Before
    public void init(){

        sessionFactory = new Configuration().configure().buildSessionFactory();

    }

    @After
    public void destroy(){


    }

    /* 插入原始数据 */
    @Test
    public void save(){

        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();

        //创建数据
        Department department = new Department("教学部");

        Post post = new Post("教学总监");

        Post post1 = new Post("java主管");

        Post post2 = new Post("java讲师");

        department.getPosts().add(post);

        department.getPosts().add(post1);

        department.getPosts().add(post2);

        session.save(department); //保存教学部

        Department department1 = new Department("职规部");

        Post post3 = new Post("职规主管");

        Post post4 = new Post("班主任");

        department1.getPosts().add(post3);

        department1.getPosts().add(post4);

        session.save(department1); //保存职规部

        Staff staff = new Staff("大表姐",department,post2);

        session.save(staff); //保存大表姐

        Staff staff1 = new Staff("何欣姐",department1,post4);

        session.save(staff1); //保存何欣姐

        transaction.commit();
    }


    @Test
    public void testDao(){

        DepartmentDao departmentDao = new DepartmentDaoImpl();

        List<Department> departmentList = departmentDao.findAll("from Department");

        for (Department department : departmentList) {

            System.out.println(department);

        }

        PostDao postDao = new PostDaoImpl();

        List<Post> postList = postDao.find("from Post", null);

        for (Post post : postList) {

            System.out.println(post);
        }

        StaffDao staffDao = new StaffDaoImpl();

        List<Staff> staffList = staffDao.find("from Staff", null);

        for (Staff staff : staffList) {

            System.out.println(staff);

        }

        //查询,名字叫大表姐的员工

        String hql = "from Staff where sname=:name";

        Map<String,Object> params = new HashMap<>();

        params.put("name","大表姐");

        Staff staff = staffDao.findSingle(hql, params);

        System.out.println(staff);
    }

    /* service层测试 */
    @Test
    public void testService(){

        DepartmentService ds = new DepartmentServiceImpl();

        List<Department> departList = ds.findAll();

        for (Department department : departList) {

            System.out.println("基础属性:" + department);

            for (Post post : department.getPosts()) {

                System.out.println("职务:" + post);

            }

        }

    }

}
