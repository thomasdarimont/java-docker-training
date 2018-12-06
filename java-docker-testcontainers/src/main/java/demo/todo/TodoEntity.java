package demo.todos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class TodoEntity {

    @Id
    @GeneratedValue
    Long id;

    String title;

    Boolean completed;
}
