package dao;

import entity.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String deptId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String deptId);

    Department selectByDeptName(String deptName);

    List<Department> selectAll();

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}