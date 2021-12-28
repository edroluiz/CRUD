package preferencial.saude.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import preferencial.saude.personapi.enums.TypeOfTeaching;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
