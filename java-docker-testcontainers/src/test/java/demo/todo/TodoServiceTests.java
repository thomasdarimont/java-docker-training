package demo.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration
@ComponentScan(basePackageClasses = TodoService.class)
class TodoServiceTests {

    private static final UUID DUMMY_ID = UUID.fromString("a81bc81b-dead-4e5d-abff-90865d1e13b1");

    @MockBean
    TodoRepository todoRepository;

    @Autowired
    TodoService todoService;


    @BeforeEach
    void setup() {

        Mockito.doAnswer(invocationOnMock -> {
            TodoEntity todo = invocationOnMock.getArgument(0);
            todo.setId(DUMMY_ID);
            return todo;
        }).when(todoRepository).save(Mockito.any(TodoEntity.class));
    }

    @Test
    void shouldReturnSavedTodo() {

        TodoEntity newTodo = new TodoEntity();
        newTodo.setTitle("New Todo");
        TodoEntity saved = todoService.save(newTodo);

        assertThat(saved).isNotNull().extracting(TodoEntity::getId).isEqualTo(DUMMY_ID);
    }

    @Test
    public void shouldRejectTodoWithMissingText() {

        assertThatThrownBy(() -> todoService.save(new TodoEntity()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("todo.title");
    }
}
