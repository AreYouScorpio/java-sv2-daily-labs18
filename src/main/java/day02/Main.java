package day02;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/books?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (SQLException sqlException) {
            throw new IllegalStateException("sss", sqlException);
        }

        Flyway flyway = Flyway.configure().locations("db/migration/bookstore").dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        BooksRepository booksRepository = new BooksRepository(dataSource);

        booksRepository.insertBook("Fekete István", "Téli berek", 3600,8);
        booksRepository.insertBook("Fekete Péter", "Kártyatrükkök", 1200,2);

        booksRepository.updatePieces(1L, 30);

        System.out.println();
    }


}
