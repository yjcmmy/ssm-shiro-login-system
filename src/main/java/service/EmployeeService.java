package service;


import dao.EmployeeMapper;
import entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    public EmployeeMapper employeeMapper;
    @Autowired
    public Md5PasswordService md5PasswordService;

    public List<Employee> getAllEmployee(){
        List<Employee> employees = employeeMapper.selectByEmployee(null);
        return employees;
    }

    public Employee getEmployeeById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    public List<Employee> getEmployeeByName(String name){
        return employeeMapper.selectByEmployee(
                new Employee(null,name,null,null,null,null,null));
    }

    public List<Employee> getEmployeeByLeaderId(Integer leaderId){
        return employeeMapper.selectByEmployee(
                new Employee(null,null,null,null,leaderId,null,null));
    }

    public void insertEmployee(Employee employee){
        employeeMapper.insertSelective(employee);
        md5PasswordService.insertmd5Password(employee.getId(),Md5PasswordService.initPassword);
    }

    public void deleteEmployeeById(Integer id){
        employeeMapper.deleteByPrimaryKey(id);
        md5PasswordService.deletemd5Password(id);
    }

    public void updateEmployee(Employee employee){
        employeeMapper.updateByPrimaryKeySelective(employee);
    }
    

}

