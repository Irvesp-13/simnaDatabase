package mx.edu.utez.simnaDatabase.models.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.simnaDatabase.models.pozos.Pozos;
import mx.edu.utez.simnaDatabase.models.user.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "people")
@NoArgsConstructor
@Setter
@Getter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String surname;
    @Column(length = 50)
    private String lastname;
    @Column(columnDefinition = "DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthdate;
    @Column(length = 18, nullable = false, unique = true)
    private String curp;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status; //Wrappers boolean
    @OneToOne(mappedBy = "person", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = {"person"})
    private User user;

    @ManyToMany
    @JoinTable(
            name = "persona_pozo",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "pozo_id")
    )
    @JsonIgnore
    private List<Pozos> pozos;
    public Person(String name, String surname, String lastname, LocalDate birthdate, String curp) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.curp = curp;
    }

    public Person(Long id, String name, String surname, String lastname, LocalDate birthdate, String curp, User user) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.curp = curp;
        this.user = user;
    }
}
