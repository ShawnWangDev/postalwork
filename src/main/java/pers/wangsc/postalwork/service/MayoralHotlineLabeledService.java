package pers.wangsc.postalwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.wangsc.postalwork.dao.MayoralHotlineLabeledDao;
import pers.wangsc.postalwork.entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public List<MayoralHotlineLabeled> findAllByAppealDateTimeBetween(String startDateStr, String endDateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        var result = mayoralHotlineLabeledDao.findAllByAppealDateTimeBetween(startDate, endDate);
        return result;
    }

    public Map<String, List<MayoralHotlineLabeled>> categorizedByExpressBrand(List<MayoralHotlineLabeled> mayoralHotlineLabeledList) {
        Map<String, List<MayoralHotlineLabeled>> map = new HashMap<>();
        for (var labeled : mayoralHotlineLabeledList) {
            String expressBrandName = labeled.getExpressBrand().getName();
            if (map.containsKey(expressBrandName)) {
                map.get(expressBrandName).add(labeled);
            } else {
                List<MayoralHotlineLabeled> list = new ArrayList<>();
                list.add(labeled);
                map.put(expressBrandName, list);
            }
        }
        return map;
    }

    public Map<String, Integer> categorizedByIssueType(List<MayoralHotlineLabeled> mayoralHotlineLabeledList) {
        Map<String, Integer> map = new HashMap<>();
        for (var labeled : mayoralHotlineLabeledList) {
            String issueTypeName = labeled.getIssueType().getName();
            int count = map.containsKey(issueTypeName) ? map.get(issueTypeName) : 0;
            map.put(issueTypeName, ++count);
        }
        return map;
    }

    public int addMissingMayoralHotline() {
        List<MayoralHotline> mayoralHotlineList = mayoralHotlineService.findAll();
        List<Integer> hotlineLabeledHotlineIds = mayoralHotlineLabeledDao.findAllMayoralHotlineId();
        List<ExpressBrand> expressBrands = expressBrandService.findAll();
        int addMissingCounter = 0;
        for (MayoralHotline mayoralHotline : mayoralHotlineList) {
            if (hotlineLabeledHotlineIds.contains(mayoralHotline.getId())) {
                hotlineLabeledHotlineIds.remove(mayoralHotline.getId());
            } else {
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
