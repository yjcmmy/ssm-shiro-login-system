package dao;

import entity.Employee;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    List<Employee> selectByEmployee(Employee employee);

    int selectCount();

    int selectMaxId();

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}