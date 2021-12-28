package preferencial.saude.personapi.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import preferencial.saude.personapi.dto.request.TeacherDTO;
import preferencial.saude.personapi.entity.Teacher;

@Mapper
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
    
    Teacher toModel(TeacherDTO dto);

    TeacherDTO toDTO(Teacher dto);
}