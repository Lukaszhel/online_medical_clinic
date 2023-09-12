package pl.zajavka.online_medical_clinic.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import pl.zajavka.online_medical_clinic.api.dto.VisitDTO;
import pl.zajavka.online_medical_clinic.business.VisitService;

@Controller
@RequiredArgsConstructor
public class AddCommentController {

   /* private static final String ADD_COMMENT =  "/add-comment/{visitNumber}";
    private static final String ADD_COMMENT_SAVE =  "/add-comment/save";
    private final VisitService visitService;

    @GetMapping(value = ADD_COMMENT)
    public String addComment(@PathVariable String visitNumber, Model model){

        model.addAttribute("visitDTO", VisitDTO.builder().visitNumber(visitNumber).build());
        return "add_comment";

    }

    @GetMapping(value = ADD_COMMENT_SAVE)
    public String saveComment(@ModelAttribute("visitDTO") VisitDTO visitDTO){
        visitService.updateVisitWithCommentByVisitNumber(visitDTO.getComment(), visitDTO.getVisitNumber());
        return "redirect:/doctor/choose";
    }*/
}
