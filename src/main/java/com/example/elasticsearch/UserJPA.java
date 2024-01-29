package com.example.elasticsearch;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
@ToString
public class UserJPA {
    @jakarta.persistence.Id
    @Id
    private String id;

    private String name;

    private Integer age;
}
