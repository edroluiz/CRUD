package preferencial.saude.personapi.service;

import lombok.AllArgsConstructor;
import preferencial.saude.personapi.dto.mapper.TeacherMapper;
import preferencial.saude.personapi.dto.request.TeacherDTO;
import preferencial.saude.personapi.dto.response.MessageResponseDTO;
import preferencial.saude.personapi.entity.Teacher;
import preferencial.saude.personapi.exception.TeacherNotFoundException;
import preferencial.saude.personapi.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Service {

    private TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper = TeacherMapper.INSTANCE;

    public MessageResponseDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacherToSave = teacherMapper.toModel(teacherDTO);

        Teacher savedTeacher = teacherRepository.save(teacherToSave);
        return MessageResponseDTO
                .builder()
                .message("Created teacher with ID " + savedTeacher.getId())
                .build();
    }

    public List<TeacherDTO> listAll(){
        List<Teacher> allPeople = teacherRepository.findAll();
        return allPeople.stream()
                .map(teacherMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Teacher verifyIfExists(Long id) throws TeacherNotFoundException {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
    }

    public TeacherDTO findById(Long id) throws TeacherNotFoundException {
        Teacher teacher = verifyIfExists(id);

        return teacherMapper.toDTO(teacher);
    }

    public void delete(Long id) throws TeacherNotFoundException {
        verifyIfExists(id);

        teacherRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, TeacherDTO teacherDTO) throws TeacherNotFoundException {
        verifyIfExists(id);

        Teacher teacherToUpdate = teacherMapper.toModel(teacherDTO);

        Teacher savedTeacher = teacherRepository.save(teacherToUpdate);
        return MessageResponseDTO
                .builder()
                .message("Created teacher with ID " + savedTeacher.getId())
                .build();
    }

}

