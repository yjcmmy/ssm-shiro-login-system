package service;

import dao.EmployeeMapper;
import dao.Md5PasswordMapper;
import entity.Employee;
import entity.Md5Password;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class Md5PasswordService {
    @Autowired
    public Md5PasswordMapper md5PasswordMapper;

    @Autowired
    public EmployeeMapper employeeMapper;

    public static String initPassword="000";

    public String getmd5Password(Integer id){
        return  md5PasswordMapper.selectByPrimaryKey(id).getPwMd5();
    }

    public void insertmd5Password(Integer id,String string){
        Md5Password md5Password = new Md5Password(id, toMd5(string, id));
        md5PasswordMapper.insert(md5Password);
    }

    public void updatemd5Password(Integer id,String string){
        Md5Password md5Password = new Md5Password(id, toMd5(string, id));
        md5PasswordMapper.updateByPrimaryKey(md5Password);
    }

    public void deletemd5Password(Integer id){
        md5PasswordMapper.deleteByPrimaryKey(id);
    }

    public void initializeAllPassword(){
        List<Employee> employees = employeeMapper.selectByEmployee(null);
        for(Employee employee:employees){
            Md5Password md5Password = md5PasswordMapper.selectByPrimaryKey(employee.getId());
            if(md5Password!=null){
                updatemd5Password(employee.getId(),initPassword);
            }else{
                insertmd5Password(employee.getId(),initPassword);
            }
        }
    }

    public static String toMd5(String s,Integer id){
        //根据用户id设置盐值
        ByteSource salt = ByteSource.Util.bytes(id.toString());
        SimpleHash md5 = new SimpleHash("MD5", s, salt, 1024);
        return md5.toString();
    }

    


}
