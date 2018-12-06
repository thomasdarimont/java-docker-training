package demo.todos;

import org.springframework.data.jpa.repository.JpaRepository;

interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
