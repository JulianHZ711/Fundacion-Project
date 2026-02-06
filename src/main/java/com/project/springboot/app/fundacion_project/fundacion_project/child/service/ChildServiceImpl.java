package com.project.springboot.app.fundacion_project.fundacion_project.child.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildRequestDto;
import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildResponseDto;
import com.project.springboot.app.fundacion_project.fundacion_project.child.model.Child;
import com.project.springboot.app.fundacion_project.fundacion_project.child.repository.ChildRepository;

@Service
public class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;

    public ChildServiceImpl(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public List<ChildResponseDto> findAll() {
        return childRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public ChildResponseDto findById(String document) {
        Child child = childRepository.findById(document)
                .orElseThrow();

        return map(child);
    }

    @Override
    public ChildResponseDto create(ChildRequestDto dto) {
    
        Child child = new Child(
            dto.getDocument(),
            dto.getFirstName(),
            dto.getLastName(),
            dto.getBirthDate(),
            dto.getGender(),
            dto.getBloodType()
        );

        return map(childRepository.save(child));
    }

    @Override
    public ChildResponseDto update(String document, ChildRequestDto dto) {
        
        Child child = childRepository.findById(document)
                .orElseThrow();

        // Actualizar todos los campos
        child.setDocument(dto.getDocument());
        child.setFirstName(dto.getFirstName());
        child.setLastName(dto.getLastName());
        child.setBirthDate(dto.getBirthDate());
        child.setGender(dto.getGender());
        child.setBloodType(dto.getBloodType());

        return map(childRepository.save(child));
    }

    @Override
    public void delete(String document) {
        childRepository.deleteById(document);
    }

    private ChildResponseDto map(Child child) {
        
        ChildResponseDto dto = new ChildResponseDto();
        dto.setDocument(child.getDocument());
        dto.setFirstName(child.getFirstName());
        dto.setLastName(child.getLastName());
        dto.setBirthDate(child.getBirthDate());
        dto.setGender(child.getGender());
        dto.setBloodType(child.getBloodType());
        dto.setAge(child.getAge());  // ← La edad se calcula automáticamente

        return dto;
    } 
}