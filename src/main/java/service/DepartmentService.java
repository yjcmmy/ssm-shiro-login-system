package service;


import dao.DepartmentMapper;
import entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class DepartmentService {
    public static Map<String,String> idtonameMap=new HashMap<>();
    public static Map<String,String> nametoidMap=new HashMap<>();
    @Autowired
    public DepartmentMapper  departmentMapper;

    public String getDeptId(String deptname){
        Department department = departmentMapper.selectByDeptName(deptname);
        return department.getDeptId();
    }

    public String getDeptName(String deptid){
        return departmentMapper.selectByPrimaryKey(deptid).getDeptName();
    }

    public void setMaps(){
        List<Department> departments = departmentMapper.selectAll();
        for(Department i:departments){
           idtonameMap.put(i.getDeptId(),i.getDeptName());
           nametoidMap.put(i.getDeptName(),i.getDeptId());
        }

    }


}
