package com.lanou.action;

import com.lanou.dao.StaffDao;
import com.lanou.dao.impl.StaffDaoImpl;
import com.lanou.domain.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.service.DepartmentService;
import com.lanou.service.impl.DepartmentServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dllo on 17/10/20.
 */
public class StaffAction extends ActionSupport {
    private String verifyCode;
    private String departId;//二级联动中的部门选中id
    private String postId;
    private Set<Post> postList;
    private List<Staff> staffList;

    @Override
    public String execute() throws Exception {
        /*跳转到员工列表首页时需要进行的操作
        * 1，获取所有员工
        * 2，获取所有部门*/
        DepartmentService departService = new DepartmentServiceImpl();
        //获取部门集合
        List<Department> departments = departService.findAll();

        //将部门集合放入request对象中的属性集合中
        ServletActionContext.getRequest().getSession()
                .setAttribute("departments", departments);


        return SUCCESS;
    }


    public String login() {

        return SUCCESS;
    }

    /**
     * 查询部门所对应的职务
     **/
    public String findPost() {
        System.out.println("选中的部门ID：" + departId);

        //通过部门id查找当前选中的部门对象
        DepartmentService departService = new DepartmentServiceImpl();

        Department selectDepart = departService.findById(
                Integer.parseInt(departId));

        //然后从部门对象中获取选中的职务集合
        postList = selectDepart.getPosts();

        return SUCCESS;
    }

    public String queryStaff(){

        StringBuffer sb = new StringBuffer();

        Map<String,Object> params = new HashMap<>();

        sb.append("from Staff where 1=1");

        if(!departId.equals("-1") && !departId.isEmpty()){

            params.put("department_id",Integer.parseInt(departId));

            sb.append(" and department_id=:department_id");

        }

        if(!postId.equals("-1") && !postId.isEmpty()){

            params.put("post_id",Integer.parseInt(postId));

            sb.append(" and post_id=:post_id");

        }

        StaffDao staffDao = new StaffDaoImpl();

        staffList = staffDao.find(sb.toString(), params);

        return SUCCESS;

    }


    public void validateLogin() {
        String code = (String) ActionContext.getContext()
                .getApplication().get("verifyCode");

        System.out.println("存储的：" + code + " 输入的：" + verifyCode);

        if (!code.equalsIgnoreCase(verifyCode)) {
            addActionError("验证码输入错误");
        }
    }

    public Set<Post> getPostList() {
        return postList;
    }

    public void setPostList(Set<Post> postList) {
        this.postList = postList;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
}