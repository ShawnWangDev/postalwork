package pers.wangsc.postalwork.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.wangsc.postalwork.entity.ExpressBrand;
import pers.wangsc.postalwork.entity.IncomingPhone;
import pers.wangsc.postalwork.entity.IssueType;
import pers.wangsc.postalwork.service.ExpressBrandService;
import pers.wangsc.postalwork.service.IncomingPhoneService;
import pers.wangsc.postalwork.service.IssueTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/incoming_phone")
public class IncomingPhoneController {
    @Autowired
    private ExpressBrandService expressBrandService;
    @Autowired
    private IssueTypeService issueTypeService;
    @Autowired
    private IncomingPhoneService incomingPhoneService;

    @GetMapping("")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("incoming_phone/home");
        mv.addObject("expressBrandList", expressBrandService.findAll());
        mv.addObject("issueTypeList", issueTypeService.findAll());
        mv.addObject("incomingPhoneList",incomingPhoneService.findAll());
        IncomingPhone incomingPhone = new IncomingPhone();
        incomingPhone.setCallInTime(new Date());
        mv.addObject("incomingPhone", incomingPhone);
        return mv;
    }

    @PostMapping(value = "/add")
    public ModelAndView add(@Valid IncomingPhone incomingPhone
            , @RequestParam("expressBrand") Integer expressBrandId
            , @RequestParam("issueType") Integer issueTypeId
            , BindingResult result) {
        if (result.hasErrors()) {
            return home();
        }
        incomingPhone.setExpressBrand(new ExpressBrand(expressBrandId, null));
        incomingPhone.setIssueType(new IssueType(issueTypeId, null));
        incomingPhone.setCreateTime(new Date());
        incomingPhoneService.save(incomingPhone);
        System.out.println(incomingPhone);
        return home();
    }
}
