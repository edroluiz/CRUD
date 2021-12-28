package preferencial.saude.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import preferencial.saude.personapi.enums.TypeOfTeaching;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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