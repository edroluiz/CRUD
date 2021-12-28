package preferencial.saude.personapi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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


