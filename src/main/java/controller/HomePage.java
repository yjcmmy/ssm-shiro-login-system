package controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import service.DepartmentService;
import service.EmployeeService;
import service.Md5PasswordService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes(value = {"msg","localpage","msg2"},types = {String.class})
@RequestMapping("/yjc")
public class HomePage {
    String homepage="homepage";

    @Autowired
    public EmployeeService employeeService;
    @Autowired
    public DepartmentService departmentService;
    @Autowired
    public Md5PasswordService md5PasswordService;

    @RequestMapping("/testmybatis")
    public String testmybatis(){
        departmentService.setMaps();
        System.out.println(employeeService.getEmployeeById(1).toString());
        System.out.println(employeeService.getEmployeeByLeaderId(1).toString());
        System.out.println(employeeService.getAllEmployee().toString());
        md5PasswordService.initializeAllPassword();
        return homepage;
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String name,
                        @RequestParam("password") String password,
                        Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        System.out.println("loginning name="+name+",password="+password);
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        //token.setRememberMe(true);
        try {
            subject.login(token);
            System.out.println("login success");
            map.put("msg","请点击登录");
            return "redirect:/yjc/homepage/1";
        } catch (UnknownAccountException e){
            //账号不对
            System.out.println("登录失败，"+e.getMessage());
            map.put("msg","登录失败，"+e.getMessage());
            return "redirect:/login.jsp";
        } catch (AuthenticationException e){
            //密码不对
            System.out.println("登录失败，密码不对");
            map.put("msg","登录失败，密码不对");
            return "redirect:/login.jsp";
        }
    }

    @RequestMapping("/homepage/{i}")
    public String getHomepage(@PathVariable("i") Integer i,
                              Map<String,Object> map){
        departmentService.setMaps();
        Employee user = (Employee) SecurityUtils.getSubject().getPrincipal();
        System.out.println("当前用户："+user);
        map.put("user",user);
        //pagenum是当前页码，pagesize是每一页个数
        Page<Object> page = PageHelper.startPage(i,5);
        //navigatepages是要显示几页
        List<Employee> allEmployee = employeeService.getAllEmployee();
        PageInfo<Object> pageInfo = new PageInfo<>(page,4);
        int[] navigatepageNums = pageInfo.getNavigatepageNums();
        int lastPage = pageInfo.getPages();
        int pageNum = pageInfo.getPageNum();
        map.put("employees",allEmployee);
        map.put("pages",navigatepageNums);
        map.put("lastpage",lastPage);
        map.put("localpage",pageNum);
        return homepage;
    }
    @RequiresRoles(value = {"boss"})
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public String insertEmployeePage(Map<String,Object> map){
        map.put("employee",new Employee());
        List<String> departmentlist=new ArrayList<>();
        for(Map.Entry<String,String> entry:DepartmentService.idtonameMap.entrySet()){
            departmentlist.add(entry.getValue());
        }
        map.put("departmentList",departmentlist);
        return "insert";
    }

    //BindingResulta用来处理数据格式化类型转换出错
    @RequiresRoles(value = {"boss"})
    @RequestMapping (value = "/insert",method = RequestMethod.POST)
    public String insertEmployee(@Valid Employee employee,
                                 BindingResult bindingResult,
                                 Map<String,Object> map,
                                 HttpSession session) {
        if(bindingResult.getErrorCount()>0){
            System.out.println("error");
            for(FieldError fieldError:bindingResult.getFieldErrors()){
                System.out.println(fieldError.getField()+":"+fieldError.getDefaultMessage());
            }
            List<String> departmentlist=new ArrayList<>();
            for(Map.Entry<String,String> entry:DepartmentService.idtonameMap.entrySet()){
                departmentlist.add(entry.getValue());
            }
            map.put("departmentList",departmentlist);
            return "insert";
        }
        employeeService.insertEmployee(employee);
        String url="redirect:/yjc/homepage/"+session.getAttribute("localpage");
        return url;
    }
    @RequiresRoles(value = {"boss"})
    @RequestMapping(value = "update/{id}",method = RequestMethod.GET)
    public String updateEmployeePage(@PathVariable("id") Integer id, Map<String,Object> map){
        Employee employee = employeeService.getEmployeeById(id);
        map.put("employee",employee);
        List<String> departmentlist=new ArrayList<>();
        for(Map.Entry<String,String> entry:DepartmentService.idtonameMap.entrySet()){
            departmentlist.add(entry.getValue());
        }
        map.put("departmentList",departmentlist);
        return "update";
    }
    @RequiresRoles(value = {"boss"})
    @RequestMapping(value ="/update",method = RequestMethod.PUT)
    public String updateEmployee(Employee employee, Map<String, Object> map,HttpSession session){
        employeeService.updateEmployee(employee);
        String url="redirect:/yjc/homepage/"+session.getAttribute("localpage");
        return url;
    }
    @RequiresRoles(value = {"boss"})
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Integer id,HttpSession session) {
        employeeService.deleteEmployeeById(id);
        String url="redirect:/yjc/homepage/"+session.getAttribute("localpage");
        return url;
    }

    @RequestMapping(value = "/updateMyself/{name}",method = RequestMethod.GET)
    public String updateMyselfPage(@PathVariable("name") String name, Map<String,Object> map){
        Employee employee = employeeService.getEmployeeByName(name).get(0);
        map.put("employee",employee);
        return "updateMyself";
    }
    @RequestMapping(value ="/updateMyself",method = RequestMethod.PUT)
    public String updateMyself(Employee employee, Map<String, Object> map,HttpSession session){
        employeeService.updateEmployee(employee);
        String url="redirect:/yjc/homepage/"+session.getAttribute("localpage");
        return url;
    }

    @RequestMapping(value = "/changePassword/{name}",method = RequestMethod.GET)
    public String changePasswordPage(@PathVariable("name") String name, Map<String,Object> map){
        Employee employee = employeeService.getEmployeeByName(name).get(0);
        map.put("employee",employee);
        return "changePassword";
    }
    @RequestMapping(value ="/changePassword",method = RequestMethod.PUT)
    public String changePassword(@RequestParam("oldpassword") String oldpassword,
                                 @RequestParam("newpassword") String newpassword,
                                 @RequestParam("userid") Integer id,
                                 Employee employee, Map<String, Object> map,HttpSession session){
        String s = md5PasswordService.getmd5Password(id);
        String oldmd5Password = Md5PasswordService.toMd5(oldpassword, id);
        if(oldmd5Password.equals(s)){
            md5PasswordService.updatemd5Password(id,newpassword);
            map.put("msg2","  ");
            System.out.println("密码修改成功为："+newpassword);
            String url="redirect:/yjc/homepage/"+session.getAttribute("localpage");
            return url;
        } else {
            map.put("msg2","密码不对");
            String url="redirect:/yjc/changePassword/"+employeeService.getEmployeeById(id).getName();
            return url;
        }

    }
}
