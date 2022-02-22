package employees;

import com.mysql.cj.jdbc.MysqlDataSource;
import employees.EmployeesDao;
import employees.EmployeesDao2;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeesDao2Test {

    private EmployeesDao2 employeesDao2;

    //2. gyak feladat Spring

    @Before
    public void init() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/employees?useUnicode=true");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        employeesDao2 = new EmployeesDao2(dataSource);

    }

    @Test
    public void testCreate(){
        employeesDao2.createEmployee("Jack Doe");

        List<String> name = employeesDao2.listEmployeeNames();

        assertEquals(List.of("Jack Doe"), name);

    }

    @Test
    public void testFindById(){
        long id = employeesDao2.createEmployee("Jack Doe");
        System.out.println(id);

        String name = employeesDao2.findEmployeeNameById(id);
        assertEquals("Jack Doe", name);
    }

}
