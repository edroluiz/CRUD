package teacher.api.personapi.controller;


import lombok.AllArgsConstructor;
import teacher.api.personapi.dto.request.TeacherDTO;
import teacher.api.personapi.dto.response.MessageResponseDTO;
import teacher.api.personapi.exception.TeacherNotFoundException;
import teacher.api.personapi.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Controller {

    private Service service;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {
        return service.createTeacher(teacherDTO);
    }

    @GetMapping("/all")
    public List<TeacherDTO> listAll(){
        return service.listAll();
    }

    @GetMapping("find/{id}")
    public TeacherDTO findById(@PathVariable Long id) throws TeacherNotFoundException {
        return service.findById(id);
    }

    @GetMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) throws TeacherNotFoundException {
        return service.updateById(id, teacherDTO);
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws TeacherNotFoundException {
        service.delete(id);
    }
}