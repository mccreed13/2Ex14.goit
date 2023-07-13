package com.example.ex14.controller;

import com.example.ex14.entity.Note;
import com.example.ex14.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/note")
public class NoteController {
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(value = "/list")
    public ModelAndView getNoteList(){
        ModelAndView result = new ModelAndView("note/noteList");
        result.addObject("noteList", noteService.listAll());
        return result;
    }

    @GetMapping(value = "/edit")
    public ModelAndView editNote(@RequestParam(name="id") Long id){
        ModelAndView result = new ModelAndView("note/noteEdit");
        result.addObject("note", noteService.getById(id));
        return result;
    }


    @PostMapping(value = "/edit")
    public String editNote(Note note){
        noteService.update(note);
        return "redirect:/note/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name="id") Long id){
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
}
