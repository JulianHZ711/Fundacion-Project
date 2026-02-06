package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.springboot.app.fundacion_project.fundacion_project.child.model.Child;
import com.project.springboot.app.fundacion_project.fundacion_project.child.repository.ChildRepository;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto.ClinicalHistoryResponseDto;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.exception.ClinicalHistoryNotFoundException;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.model.ClinicalHistory;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.repository.ClinicalHistoryRepository;

@Service
public class ClinicalHistoryServiceImpl implements ClinicalHistoryService {

    private final ClinicalHistoryRepository historyRepository;
    private final ChildRepository childRepository;

    public ClinicalHistoryServiceImpl(
            ClinicalHistoryRepository historyRepository,
            ChildRepository childRepository) {

        this.historyRepository = historyRepository;
        this.childRepository = childRepository;
    }

    @Override
    public ClinicalHistoryResponseDto upload(String childDocument, MultipartFile file, String fileName) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("PDF file is required");
        }

        if (!file.getContentType().equals("application/pdf")) {
            throw new RuntimeException("Only PDF files are allowed");
        }

        Child child = childRepository.findById(childDocument)
                .orElseThrow(() -> new RuntimeException("Child not found"));

        ClinicalHistory history = new ClinicalHistory();
        history.setFileName(fileName);
        history.setFileType(file.getContentType());
        history.setUploadDate(LocalDate.now());
        history.setFile(file.getBytes());
        history.setChild(child);

        history = historyRepository.save(history);

        return mapToDto(history);
    }

    @Override
    public List<ClinicalHistoryResponseDto> findByChild(String childDocument) {

        if (!childRepository.existsById(childDocument)) {
            throw new RuntimeException("Child not found");
        }

        return historyRepository.findByChild_Document(childDocument)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClinicalHistoryResponseDto findById(Long id) {
        ClinicalHistory history = historyRepository.findById(id)
                .orElseThrow(() -> new ClinicalHistoryNotFoundException(id));

        return mapToDto(history);
    }

    @Override
    public ClinicalHistory findEntityById(Long id) {
        return historyRepository.findById(id)
                .orElseThrow(() -> new ClinicalHistoryNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        ClinicalHistory history = historyRepository.findById(id)
                .orElseThrow(() -> new ClinicalHistoryNotFoundException(id));

        historyRepository.delete(history);
    }

    private ClinicalHistoryResponseDto mapToDto(ClinicalHistory history) {
        ClinicalHistoryResponseDto dto = new ClinicalHistoryResponseDto();

        dto.setId(history.getId());
        dto.setFileName(history.getFileName());
        dto.setFileType(history.getFileType());
        dto.setUploadDate(history.getUploadDate());
        dto.setChildDocument(history.getChild().getDocument());
        

        return dto;
    }
}
