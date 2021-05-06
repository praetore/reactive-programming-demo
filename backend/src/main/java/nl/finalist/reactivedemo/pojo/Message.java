package nl.finalist.reactivedemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Message {
    @Id
    private Long id;
    private String message;
    private LocalDateTime date = LocalDateTime.now();
    private String author;
}
