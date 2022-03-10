package teacher.api.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teacher.api.personapi.enums.TypeOfTeaching;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineDTO {

    private Long id;

    @NotEmpty
    private String disciplineName;

    @Enumerated(EnumType.STRING)
    private TypeOfTeaching type_of_teaching;

    @NotEmpty
    private String workload;
}
