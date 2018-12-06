package demo.todo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.UUID;


@SpringBootTest
@Transactional
@ContextConfiguration(initializers = TodoServiceIT.Initializer.class)
class TodoServiceIT {

    private static PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("training/postgres-todo-testdb");

//    @BeforeAll
//    public static void initDatabase() {
//        POSTGRES.start();
//    }

    @BeforeEach
    public void startDatabase() {
        POSTGRES.start();
    }

    @AfterEach
    public void stopDatabase() {
        POSTGRES.stop();
    }

    @Autowired
    private TodoService todoService;

    @Test
    void shouldFindExistingData() {

        TodoEntity found = todoService.getById(UUID.fromString("40e6215d-b5c6-4896-987c-f30f3678f608"));

        Assertions.assertThat(found) //
                .isNotNull() //
                .extracting(TodoEntity::getTitle).isEqualTo("Todo 1") //
        ;
    }

    @Test
    void shouldSaveAndLoadNewData() {

        TodoEntity todo = new TodoEntity();
        todo.setTitle("Todo 3");
        todo.setCompleted(false);

        TodoEntity saved = todoService.save(todo);

        TodoEntity found = todoService.getById(saved.getId());
        Assertions.assertThat(found).isNotNull();
    }


    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {

            // Use dynamic postgres container as datasource for tests.

//            TestPropertyValues.of( //
//                    "spring.datasource.url=" + POSTGRES.getJdbcUrl(), //
//                    "spring.datasource.username=" + POSTGRES.getUsername(), //
//                    "spring.datasource.password=" + POSTGRES.getPassword() //
//            ).applyTo(applicationContext);


            TestPropertyValues.of( //
                    "spring.datasource.url=jdbc:tc:postgresql:11.1-alpine://localhost/test?TC_INITSCRIPT=db/data.sql", // + POSTGRES.getJdbcUrl(), //
                    "spring.datasource.username=" + POSTGRES.getUsername(), //
                    "spring.datasource.password=" + POSTGRES.getPassword(), //
                    "spring.datasource.driverClassName=org.testcontainers.jdbc.ContainerDatabaseDriver"
            ).applyTo(applicationContext);
        }
    }
}
