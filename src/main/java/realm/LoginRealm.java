package realm;


import entity.Employee;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import service.DepartmentService;
import service.EmployeeService;
import service.Md5PasswordService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoginRealm extends AuthorizingRealm {
    @Autowired
    public EmployeeService employeeService;
    @Autowired
    public DepartmentService departmentService;
    @Autowired
    public Md5PasswordService md5PasswordService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Employee primaryPrincipal = (Employee)principalCollection.getPrimaryPrincipal();
        System.out.println(primaryPrincipal.toString());
        System.out.println(primaryPrincipal);
        Set<String> roles=new HashSet<>();
        roles.add("employee");
        Integer leaderId = primaryPrincipal.getLeaderId();
        System.out.println(leaderId);
        if(leaderId==null) {
            roles.add("manager");
            roles.add("boss");
        }else if (leaderId.equals(1)){
            roles.add("manager");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        List<Employee> employees = employeeService.getEmployeeByName(username);
        if(employees.size()==0){
            throw new UnknownAccountException("用户不存在");
        }
        Integer id=employees.get(0).getId();
        String password=md5PasswordService.getmd5Password(id);
        ByteSource salt = ByteSource.Util.bytes(id.toString());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employees.get(0), password, salt, "1024");
        return info;
    }
}
