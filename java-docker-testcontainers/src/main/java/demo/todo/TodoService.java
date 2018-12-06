package demo.todo;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    TodoEntity save(TodoEntity todo);

    TodoEntity getById(UUID id);

    List<TodoEntity> findAll();
}
