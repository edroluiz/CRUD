package teacher.api.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeOfTeaching {

    EAD("Remote"),
    Presencial("On-site");

    private final String description;
}
