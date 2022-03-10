package teacher.api.personapi.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import teacher.api.personapi.dto.request.TeacherDTO;
import teacher.api.personapi.entity.Teacher;

@Mapper
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
    
    Teacher toModel(TeacherDTO dto);

    TeacherDTO toDTO(Teacher dto);
}