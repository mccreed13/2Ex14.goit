package com.example.ex14.service;

import com.example.ex14.entity.Note;
import com.example.ex14.exception.NoteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private Map<Long,Note> noteMap;

    public NoteService(){
        this.noteMap = new HashMap<>();
        add(new Note(2L, "Title", "Context"));
        add(new Note(2L, "Title2", "Context2"));
        add(new Note(2L, "Title3", "Context3"));
    }

    public Map<Long,Note> listAll(){
        return noteMap;
    }

    public Note add(Note note){
        note.setId(noteMap.size()+1L);
        noteMap.put(note.getId(), note);
        return note;
    }

    public void deleteById(Long id){
        Note note = noteMap.get(id);
        if(note == null){
            throw new NoteNotFoundException("Can not delete by id");
        }
        noteMap.remove(id, note);
    }

    public void delete(Note note){
        if(!noteMap.containsValue(note)){
            throw new NoteNotFoundException("Can not delete");
        }
        noteMap.remove(note.getId(), note);
    }

    public void update(Note note){
        if(noteMap.containsKey(note.getId())){
            noteMap.replace(note.getId(), note);
        } else throw new NoteNotFoundException("Can not update");
    }

    public Note getById(Long id){
        Note note = noteMap.get(id);
        if(note == null) throw new NoteNotFoundException("Can not get");
        return noteMap.get(id);
    }
}
