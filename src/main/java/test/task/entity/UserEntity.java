package test.task.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column()
    private String name;

    @Column()
    private String email;

    @Column()
    private int age;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UserEntity() {}

    public UserEntity(String name, String email, int age, LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        if (!(age > 130)) {
            this.age = age;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return "---------------------------\n" +
                "Пользователь № " + id + '\n'
                + "Имя: " + name + '\n'
                + "Почта: " + email + '\n'
                + "Возраст: " + age + '\n'
                + "Созда: " + createdAt + '\n'
                + "---------------------------";
    }
}
