package aston.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "films")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer duration;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_user",
            joinColumns = { @JoinColumn(name = "film_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")})
    private Set<User> users = new HashSet<>();

    public void addLike(User user) {
        users.add(user);
    }

    public void removeLike(User user) {
        users.remove(user);
    }
}