package com.example.ex14.service;

import com.example.ex14.entity.Note;
import com.example.ex14.exception.NoteNotFoundException;
import com.example.ex14.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
        Note note2 = getById(note.getId());
        if(note2 == null){
            throw new NoteNotFoundException("note not found");
        }
        BeanUtils.copyProperties(note, note2);
        noteRepository.save(note2);
    }

    public Note getById(Long id){
       String query = "select c.id, c.title, c.content from note c where id=:id";
       return jdbcTemplate.queryForObject(query,
               Map.of("id", id),
               (resultSet, index) ->{
                return Note.of(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content")
                );
               });
    }
}
