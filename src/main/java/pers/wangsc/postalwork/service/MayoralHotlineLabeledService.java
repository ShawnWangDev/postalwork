package pers.wangsc.postalwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pers.wangsc.postalwork.dao.MayoralHotlineLabeledDao;
import pers.wangsc.postalwork.entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MayoralHotlineLabeledService {
    @Autowired
    private MayoralHotlineLabeledDao mayoralHotlineLabeledDao;
    @Autowired
    ExpressBrandService expressBrandService;
    @Autowired
    private MayoralHotlineService mayoralHotlineService;

//    public List<MayoralHotlineLabeled> findByMayoralHotlineOrderByDeadlineDateTimeDesc(int pageNum, int pageSize) {
//        List<MayoralHotlineLabeled> hotlineLabeledList = new ArrayList<>();
//        Page<MayoralHotline> mayoralHotlinePage = mayoralHotlineService.findIdByOrderByDeadlineDateTimeDesc(pageNum, pageSize);
//        for (MayoralHotline mayoralHotline : mayoralHotlinePage) {
//            hotlineLabeledList.add(mayoralHotlineLabeledDao.findByMayoralHotlineId(mayoralHotline.getId()));
//        }
//        return hotlineLabeledList;
//    }

    public MayoralHotlineLabeled findByMayoralHotlineId(Integer id) {
        return mayoralHotlineLabeledDao.findByMayoralHotlineId(id);
    }

    public int addMissingMayoralHotline() {
        List<MayoralHotline> mayoralHotlineList = mayoralHotlineService.findAll();
        List<Integer> hotlineLabeledHotlineIds = mayoralHotlineLabeledDao.findAllMayoralHotlineId();
        List<ExpressBrand> expressBrands = expressBrandService.findAll();
        int addMissingCounter = 0;
        for (MayoralHotline mayoralHotline : mayoralHotlineList) {
            if (hotlineLabeledHotlineIds.contains(mayoralHotline.getId())) {
                hotlineLabeledHotlineIds.remove(mayoralHotline.getId());
            }else{
                MayoralHotlineLabeled labeled = new MayoralHotlineLabeled();
                labeled.setMayoralHotline(mayoralHotline);
                labeled.setExpressBrand(expressBrands);
                labeled.setIssueCondition(new IssueCondition(1));
                labeled.setIssueType(new IssueType(1));
                mayoralHotlineLabeledDao.save(labeled);
                addMissingCounter++;
            }
        }
        return addMissingCounter;
    }

    public void update(MayoralHotlineLabeled arg) {
        Optional<MayoralHotlineLabeled> optionalHotlineLabeled = mayoralHotlineLabeledDao.findById(arg.getId());
        if (optionalHotlineLabeled.isPresent()) {
            var labeled = optionalHotlineLabeled.get();
            labeled.setUpdateDateTime(new Date());
            if (arg.getIssueCondition() != null)
                labeled.setIssueCondition(arg.getIssueCondition());
            if (arg.getIssueType() != null)
                labeled.setIssueType(arg.getIssueType());
            if (arg.getExpressBrand() != null)
                labeled.setExpressBrand(arg.getExpressBrand());
            if (arg.getAdditionalRemark() != null) {
                labeled.setAdditionalRemark(arg.getAdditionalRemark());
            }
            mayoralHotlineLabeledDao.save(labeled);
        }
    }
}
