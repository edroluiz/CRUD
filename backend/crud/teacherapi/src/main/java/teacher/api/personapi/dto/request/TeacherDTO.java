package teacher.api.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String registration;

    @NotEmpty
    private List<DisciplineDTO> disciplines;
}


