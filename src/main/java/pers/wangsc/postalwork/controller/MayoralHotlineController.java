package pers.wangsc.postalwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import pers.wangsc.edocument.util.StringUtil;
import pers.wangsc.edocument.word.Word;
import pers.wangsc.postalwork.entity.ExpressBrand;
import pers.wangsc.postalwork.entity.MayoralHotline;
import pers.wangsc.postalwork.service.ExpressBrandService;
import pers.wangsc.postalwork.util.mayoralhotline.FilesComparison;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mayoralhotline")
public class MayoralHotlineController {

    @Autowired
    private ExpressBrandService expressBrandService;

    @Value("${mayoralhotline.compare.responded}")
    String respondedDir;
    @Value("${mayoralhotline.compare.received}")
    String receivedDir;

    @Value("${mayoralhotline.files.table.location}")
    private String tableLocation;

    @Value("${mayoralhotline.files.directory}")
    private String filesDirectory;


    @GetMapping("/list")
    public ModelAndView list() {
        var expressBrandList = expressBrandService.findAll();
        List<MayoralHotline> mayoralHotlineList = new ArrayList<>();
        int[] tableLocations = StringUtil.delimiterStringToNoOffsetIntArray(tableLocation);
        File[] files = new File(filesDirectory).listFiles();
        for (File f : files) {
            Word word = new Word(f.getPath());
            var data = word.getTableContent(0, tableLocations);
            MayoralHotline hotline = new MayoralHotline(data);
            hotline.setExpressBrand(expressBrandList);
            mayoralHotlineList.add(hotline);
        }
        mayoralHotlineList.forEach(System.out::println);
        ModelAndView mv = new ModelAndView("mayoral_hotline/list");
        return mv;
    }

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
