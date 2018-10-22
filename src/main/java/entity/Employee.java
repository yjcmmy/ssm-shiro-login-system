package entity;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    @NotNull(message = "工号不能为空")
    private Integer id;
    @NotNull(message = "用户名不能为空")
    @Size(min = 1,max = 4,message = "长度应该在{min}-{max}个字符")
    private String name;
    @Email
    @NotNull(message = "邮箱不能为空")
    private String email;
    @NotNull(message = "入职日期不能为空")
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private Integer leaderId;

    private String department;
    @NumberFormat(pattern = "#,###,###.#")
    private Integer salary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", leaderId=" + leaderId +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Employee() {
    }

    public Employee(Integer id, String name, String email, Date birth, Integer leaderId, String department, Integer salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.leaderId = leaderId;
        this.department = department;
        this.salary = salary;
    }
}