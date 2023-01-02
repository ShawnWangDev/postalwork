package pers.wangsc.postalwork.controller;

import org.springframework.web.bind.annotation.*;
import pers.wangsc.postalwork.util.mayoralhotline.FilesComparison;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mayoral_hotline")
public class MayoralHotlineController {
    @Value("${mayoral_hotline.compare.responded}")
    String respondedDir;
    @Value("${mayoral_hotline.compare.received}")
    String receivedDir;

    @RequestMapping("/file_lists_comparison")
    public ModelAndView fileListsComparison() {
        FilesComparison billComparison = new FilesComparison(receivedDir, respondedDir);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mayoral_hotline/file_lists_comparison");
        mv.addObject("receivedNotHas", billComparison.receivedNotHas());
        mv.addObject("respondedNotHas", billComparison.respondedNotHas());
        return mv;
    }

    @RequestMapping("/text_preprocess")
    public String textPreprocess() {
        return "mayoral_hotline/text_preprocess";
    }
}
