package pers.wangsc.postalwork.util.mayoralhotlineissue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.wangsc.postalwork.dao.ExpressBrandDao;
import pers.wangsc.postalwork.dao.IssueTypeDao;
import pers.wangsc.postalwork.entity.ExpressBrand;
import pers.wangsc.postalwork.entity.IssueType;
import pers.wangsc.postalwork.entity.MayoralHotlineIssue;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Analysis {
    private static ExpressBrandDao expressBrandDao;
    private static IssueTypeDao issueTypeDao;

    private List<Map.Entry<String,List<MayoralHotlineIssue>>> expressBrandMapEntryList;

    private List<Map.Entry<String,List<MayoralHotlineIssue>>> issueTypeMapEntryList;
    private Map<String, List<MayoralHotlineIssue>> expressBrandMap;
    private Map<String, List<MayoralHotlineIssue>> issueTypeMap;

    public Analysis build(List<MayoralHotlineIssue> list) {
        Map<String, List<MayoralHotlineIssue>> unsortedExpressBrandMap = new HashMap<>();
        Map<String, List<MayoralHotlineIssue>> unsortedIssueTypeMap = new HashMap<>();
        for (ExpressBrand expressBrand : expressBrandDao.findAll()) {
            unsortedExpressBrandMap.put(expressBrand.getName(), new ArrayList<>());
        }
        for (IssueType issueType : issueTypeDao.findAll()) {
            unsortedIssueTypeMap.put(issueType.getName(), new ArrayList<>());
        }
        list.removeIf(ele -> ele.getExpressBrand().getName().equals("{{多家}}"));
        for (var issue : list) {
            String expressBrandName = issue.getExpressBrand().getName();
            String issueTypeName = issue.getIssueType().getName();
            if (unsortedExpressBrandMap.containsKey(expressBrandName)) {
                unsortedExpressBrandMap.get(expressBrandName).add(issue);
            }
            if (unsortedIssueTypeMap.containsKey(issueTypeName)) {
                unsortedIssueTypeMap.get(issueTypeName).add(issue);
            }
        }
        unsortedExpressBrandMap.entrySet().removeIf(entry -> entry.getValue().size() == 0);
        unsortedIssueTypeMap.entrySet().removeIf(entry -> entry.getValue().size() == 0);

        expressBrandMapEntryList=sort(unsortedExpressBrandMap);
        issueTypeMapEntryList=sort(unsortedIssueTypeMap);
        expressBrandMap = descSort(unsortedExpressBrandMap);
        issueTypeMap = descSort(unsortedIssueTypeMap);
        return this;
    }

    private List<Map.Entry<String,List<MayoralHotlineIssue>>> sort(Map<String,List<MayoralHotlineIssue>> map){
        return map.entrySet().stream().sorted((o1, o2) -> o2.getValue().size() - o1.getValue().size()).toList();
    }

    private Map<String,List<MayoralHotlineIssue>> descSort(Map<String,List<MayoralHotlineIssue>> map){
        return map.entrySet().stream().sorted((o1, o2) -> o2.getValue().size() - o1.getValue().size())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    @Autowired
    public void setExpressBrandDao(ExpressBrandDao expressBrandDao) {
        Analysis.expressBrandDao = expressBrandDao;
    }

    @Autowired
    public void setIssueTypeDao(IssueTypeDao issueTypeDao) {
        Analysis.issueTypeDao = issueTypeDao;
    }

    public Map<String, List<MayoralHotlineIssue>> getExpressBrandMap() {
        return expressBrandMap;
    }

    public Map<String, List<MayoralHotlineIssue>> getIssueTypeMap() {
        return issueTypeMap;
    }

    public List<Map.Entry<String, List<MayoralHotlineIssue>>> getExpressBrandMapEntryList() {
        return expressBrandMapEntryList;
    }

    public List<Map.Entry<String, List<MayoralHotlineIssue>>> getIssueTypeMapEntryList() {
        return issueTypeMapEntryList;
    }
}
