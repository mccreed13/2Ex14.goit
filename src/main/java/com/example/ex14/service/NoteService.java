package com.example.ex14.service;

import com.example.ex14.entity.Note;
import com.example.ex14.exception.NoteNotFoundException;
import com.example.ex14.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<Note> listAll(){
        return noteRepository.findAll();
    }

    public Note add(Note note){
        noteRepository.save(note);
        return note;
    }

    public void deleteById(Long id){
        noteRepository.deleteById(id);
    }

    public void delete(Note note){
        noteRepository.delete(note);
    }

    public void update(Note note){
        if(note.getId() == null){
            throw new NoteNotFoundException("ID required");
        }
        Note note2 = noteRepository.getById(note.getId());
        if(note2 == null){
            throw new NoteNotFoundException("note not found");
        }
        BeanUtils.copyProperties(note, note2);
        noteRepository.save(note2);
    }

    public Note getById(Long id){
        return noteRepository.getById(id);
    }


}
