package demo.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface TodoRepository extends JpaRepository<TodoEntity, UUID> {
}
