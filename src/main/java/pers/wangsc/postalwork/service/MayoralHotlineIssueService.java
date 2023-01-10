package pers.wangsc.postalwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.wangsc.postalwork.dao.MayoralHotlineDao;
import pers.wangsc.postalwork.dao.MayoralHotlineIssueDao;
import pers.wangsc.postalwork.entity.*;
import pers.wangsc.postalwork.util.mayoralhotlineissue.StatisticStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MayoralHotlineIssueService {
    @Autowired
    private MayoralHotlineIssueDao mayoralHotlineIssueDao;
    @Autowired
    ExpressBrandService expressBrandService;
    @Autowired
    private MayoralHotlineDao mayoralHotlineDao;

    private final String MULTIPLE_EXPRESS_DATABASE_VALUE = "{{多家}}";

    public MayoralHotlineIssue findByMayoralHotlineId(Integer id) {
        return mayoralHotlineIssueDao.findByMayoralHotlineId(id);
    }

    public Map<String, List<MayoralHotlineIssue>> categorizedByExpressBrand(List<MayoralHotlineIssue> mayoralHotlineIssueList) {
        Map<String, List<MayoralHotlineIssue>> map = new HashMap<>();
        for (var issue : mayoralHotlineIssueList) {
            String expressBrandName = issue.getExpressBrand().getName();
            if (map.containsKey(expressBrandName)) {
                map.get(expressBrandName).add(issue);
            } else {
                List<MayoralHotlineIssue> list = new ArrayList<>();
                list.add(issue);
                map.put(expressBrandName, list);
            }
        }
        return map;
    }

    public Map<String, Integer> categorizedByIssueType(List<MayoralHotlineIssue> mayoralHotlineIssueList) {
        Map<String, Integer> map = new HashMap<>();
        for (var issue : mayoralHotlineIssueList) {
            String issueTypeName = issue.getIssueType().getName();
            int count = map.containsKey(issueTypeName) ? map.get(issueTypeName) : 0;
            map.put(issueTypeName, ++count);
        }
        return map;
    }

    public MayoralHotlineIssue setByMayoralHotline(MayoralHotline hotline) {
        var expressBrandList = expressBrandService.findAll();
        Integer multipleExpressBrandId = expressBrandList.stream()
                .filter(e -> e.getName().equals(MULTIPLE_EXPRESS_DATABASE_VALUE)).findAny().get().getId();
        MayoralHotlineIssue issue = new MayoralHotlineIssue();
        issue.setMayoralHotline(hotline);
        issue.setExpressBrand(expressBrandList, multipleExpressBrandId);
        issue.setIssueCondition(new IssueCondition(1));
        issue.setIssueType(new IssueType(1));
        return issue;
    }

    public int addMissingMayoralHotline() {
        List<MayoralHotline> mayoralHotlineList = mayoralHotlineDao.findAll();
        List<Integer> hotlineIssueHotlineIds = mayoralHotlineIssueDao.findAllMayoralHotlineId();
        int addMissingCounter = 0;
        for (MayoralHotline mayoralHotline : mayoralHotlineList) {
            if (hotlineIssueHotlineIds.contains(mayoralHotline.getId())) {
                hotlineIssueHotlineIds.remove(mayoralHotline.getId());
            } else {
                var issue = setByMayoralHotline(mayoralHotline);
                mayoralHotlineIssueDao.save(issue);
                addMissingCounter++;
            }
        }
        return addMissingCounter;
    }

    public void update(MayoralHotlineIssue arg) {
        Optional<MayoralHotlineIssue> optionalHotlineIssue = mayoralHotlineIssueDao.findById(arg.getId());
        if (optionalHotlineIssue.isPresent()) {
            var issue = optionalHotlineIssue.get();
            issue.setUpdateDateTime(new Date());
            if (arg.getIssueCondition() != null)
                issue.setIssueCondition(arg.getIssueCondition());
            if (arg.getIssueType() != null)
                issue.setIssueType(arg.getIssueType());
            if (arg.getExpressBrand() != null)
                issue.setExpressBrand(arg.getExpressBrand());
            if (arg.getAdditionalRemark() != null) {
                issue.setAdditionalRemark(arg.getAdditionalRemark());
            }
            mayoralHotlineIssueDao.save(issue);
        }
    }

    public void save(MayoralHotlineIssue issue){
        mayoralHotlineIssueDao.save(issue);
    }

    public List<MayoralHotlineIssue> findAllByAppealDateTimeBetween(Date startDate, Date endDate) {
        return mayoralHotlineIssueDao.findAllByAppealDateTimeBetween(startDate, endDate);
    }
    public StatisticStrategy getStatisticStrategy(String startDateStr, String endDateStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        var issueList = findAllByAppealDateTimeBetween(startDate, endDate);
        StatisticStrategy strategy=new StatisticStrategy(issueList);
        return strategy;
    }
}
