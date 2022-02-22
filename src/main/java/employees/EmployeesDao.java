package employees;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class EmployeesDao {

    private JdbcTemplate jdbcTemplate;

    public EmployeesDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createEmployee(String name){
        jdbcTemplate.update("insert into employees(emp_name) values(?)", name);
    }

    public List<String> listEmployeeNames(){
        return jdbcTemplate.query("select emp_name from employees", (rs,rowNum)->rs.getString("emp_name"));
    }

}
