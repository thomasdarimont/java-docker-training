package demo.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    @Transactional
    public TodoEntity save(TodoEntity todo) {

        if (!StringUtils.hasText(todo.getTitle())) {
            throw new IllegalArgumentException("todo.title must not be empty!");
        }

        return todoRepository.save(todo);
    }

    @Override
    public TodoEntity getById(UUID id) {
        return todoRepository.getOne(id);
    }

    @Override
    public List<TodoEntity> findAll() {
        return todoRepository.findAll();
    }
}
