package pers.wangsc.postalwork.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pers.wangsc.postalwork.entity.MayoralHotline;
import pers.wangsc.postalwork.entity.MayoralHotlineIssue;
import pers.wangsc.postalwork.service.*;
import pers.wangsc.postalwork.util.mayoralhotlineissue.Analysis;

import java.util.*;

@Controller
@RequestMapping("/mayoral_hotline_issue")
public class MayoralHotlineIssueController {
    @Value("${mayoral_hotline.files.table.location}")
    private String tableLocation;

    @Value("${mayoral_hotline.files.directory}")
    private String filesDirectory;
    @Autowired
    private ExpressBrandService expressBrandService;
    @Autowired
    private MayoralHotlineService mayoralHotlineService;
    @Autowired
    private IssueTypeService issueTypeService;
    @Autowired
    private IssueConditionService issueConditionService;
    @Autowired
    private MayoralHotlineIssueService mayoralHotlineIssueService;

    @PostMapping("/add_missing_mayoral_hotline")
    public ModelAndView addMissingMayoralHotline() {
        mayoralHotlineService.saveFromFiles(filesDirectory, tableLocation);
        int addMissingMayoralHotlineAmount = mayoralHotlineIssueService.addMissingMayoralHotline();
        ModelAndView mvList = list(1);
        mvList.addObject("addMissingMayoralHotlineAmount", addMissingMayoralHotlineAmount);
        return mvList;
    }

    @GetMapping("/add_missing_mayoral_hotline")
    public ModelAndView addMissingMayoralHotlineGetMapping() {
        return addMissingMayoralHotline();
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page") int page) {
        int addedFilesCounter = mayoralHotlineService.saveFromFiles(filesDirectory, tableLocation);
        if (addedFilesCounter > 0) {
            System.out.printf("added %d records.\n", addedFilesCounter);
        }
        List<MayoralHotlineIssue> hotlineIssueList = new ArrayList<>();
        Page<MayoralHotline> mayoralHotlinePage = mayoralHotlineService.findIdByOrderByDeadlineDateTimeDesc(page - 1, 100);
        for (MayoralHotline mayoralHotline : mayoralHotlinePage) {
            var issue = mayoralHotlineIssueService.findByMayoralHotlineId(mayoralHotline.getId());
            if (issue != null) {
                hotlineIssueList.add(issue);
            }
        }
        ModelAndView mv = new ModelAndView("mayoral_hotline/list");
        mv.addObject("hotlineIssueList", hotlineIssueList);
        mv.addObject("totalPage", mayoralHotlinePage.getTotalPages());
//        mv.addObject("addedFilesCounter", addedFilesCounter);
        mv.addObject("expressBrandList", expressBrandService.findAll());
        mv.addObject("issueConditionList", issueConditionService.findAll());
        mv.addObject("issueTypeList", issueTypeService.findAll());
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView update(@RequestBody MayoralHotlineIssue mayoralHotlineIssue) {
        mayoralHotlineIssueService.update(mayoralHotlineIssue);
        return list(1);
    }

    @GetMapping("/statistic")
    public ModelAndView statistic() {
        ModelAndView mv = new ModelAndView("mayoral_hotline/statistic");
        return mv;
    }

    @GetMapping("/statistic_strategy")
    public ModelAndView statisticStrategy() {
        ModelAndView mv = new ModelAndView("mayoral_hotline/statistic_strategy");
        return mv;
    }

    @GetMapping("/statistic_strategy_preview")
    public ModelAndView statisticStrategyPreview(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        var strategy = mayoralHotlineIssueService.getStatisticStrategy(startDate, endDate);
        var view = statisticStrategy();
        view.addObject("validList", strategy.getValidList());
        view.addObject("unsetList", strategy.getToBeDoneList());
        view.addObject("invalidList", strategy.getInvalidList());
        view.addObject("paramStartDate", startDate);
        view.addObject("paramEndDate", endDate);
        return view;
    }

    @GetMapping("/show_analysis")
    public ModelAndView showAnalysis(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        ModelAndView mv = new ModelAndView("mayoral_hotline/analysis");
        var strategy = mayoralHotlineIssueService.getStatisticStrategy(startDate, endDate);
        List<MayoralHotlineIssue> list = new ArrayList<>();
        list.addAll(strategy.getToBeDoneList());
        list.addAll(strategy.getValidList());
        Analysis analysis = new Analysis().build(list);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mv.addObject("expressBrandMapEntryListJson", mapper.writeValueAsString(analysis.getExpressBrandMapEntryList()));
            mv.addObject("issueTypeMapEntryListJson", mapper.writeValueAsString(analysis.getIssueTypeMapEntryList()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        mv.addObject("expressBrandMap", analysis.getExpressBrandMap());
        mv.addObject("issueTypeMap", analysis.getIssueTypeMap());
        mv.addObject("invalidList", strategy.getInvalidList());
        return mv;
    }
}
