package typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;
import service.DepartmentService;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DepartmentTypeHandler implements TypeHandler<String> {
    /*
    这里用@Autowired没用，用了两个map来代替
    @Autowired
    public DepartmentService departmentService;
    */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
         preparedStatement.setString(i,DepartmentService.nametoidMap.get(s));
    }
    @Override
    public String getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        if(string!=null){
            return DepartmentService.idtonameMap.get(string);
        } else {
            return  null;
        }
    }
    @Override
    public String getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        if(string!=null){
            return DepartmentService.idtonameMap.get(string);
        } else {
            return  null;
        }
    }

    @Override
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        if(string!=null){
            return DepartmentService.idtonameMap.get(string);
        } else {
            return  null;
        }
    }
}
