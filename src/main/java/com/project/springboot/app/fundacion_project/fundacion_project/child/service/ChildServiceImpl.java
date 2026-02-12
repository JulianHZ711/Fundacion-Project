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
        
        Child child = new Child();
        child.setDocument(dto.getDocument());
        child.setTipoDocumento(dto.getTipoDocumento());
        child.setFirstName(dto.getFirstName());
        child.setLastName(dto.getLastName());
        child.setBirthDate(dto.getBirthDate());
        child.setGender(dto.getGender());
        child.setBloodType(dto.getBloodType());
        child.setFechaIngreso(dto.getFechaIngreso());
        child.setEstado(dto.getEstado());
        child.setObservaciones(dto.getObservaciones());

        return map(childRepository.save(child));
    }

    @Override
    public ChildResponseDto update(String document, ChildRequestDto dto) {
        
        Child child = childRepository.findById(document)
                .orElseThrow();

        child.setDocument(dto.getDocument());
        child.setTipoDocumento(dto.getTipoDocumento());
        child.setFirstName(dto.getFirstName());
        child.setLastName(dto.getLastName());
        child.setBirthDate(dto.getBirthDate());
        child.setGender(dto.getGender());
        child.setBloodType(dto.getBloodType());
        child.setFechaIngreso(dto.getFechaIngreso());
        child.setEstado(dto.getEstado());
        child.setObservaciones(dto.getObservaciones());

        return map(childRepository.save(child));
    }

    @Override
    public void delete(String document) {
        childRepository.deleteById(document);
    }

    private ChildResponseDto map(Child child) {
        
        ChildResponseDto dto = new ChildResponseDto();
        dto.setDocument(child.getDocument());
        dto.setTipoDocumento(child.getTipoDocumento());
        dto.setFirstName(child.getFirstName());
        dto.setLastName(child.getLastName());
        dto.setBirthDate(child.getBirthDate());
        dto.setGender(child.getGender());
        dto.setBloodType(child.getBloodType());
        dto.setFechaIngreso(child.getFechaIngreso());
        dto.setEstado(child.getEstado());
        dto.setObservaciones(child.getObservaciones());
        dto.setAge(child.getAge());

        return dto;
    } 
}