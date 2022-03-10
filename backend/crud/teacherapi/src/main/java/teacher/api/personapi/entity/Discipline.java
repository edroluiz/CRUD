package teacher.api.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teacher.api.personapi.enums.TypeOfTeaching;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String disciplineName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeOfTeaching type_of_teaching;

    @Column(name = "workload")
    private String workload;
}