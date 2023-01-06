package pers.wangsc.postalwork.util.mayoralhotlinelabeled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.wangsc.postalwork.dao.ExpressBrandDao;
import pers.wangsc.postalwork.dao.IssueTypeDao;
import pers.wangsc.postalwork.entity.ExpressBrand;
import pers.wangsc.postalwork.entity.IssueType;
import pers.wangsc.postalwork.entity.MayoralHotline;
import pers.wangsc.postalwork.entity.MayoralHotlineLabeled;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Analysis {
    private static ExpressBrandDao expressBrandDao;
    private static IssueTypeDao issueTypeDao;

    private List<Map.Entry<String,List<MayoralHotlineLabeled>>> expressBrandMapEntryList;

    private List<Map.Entry<String,List<MayoralHotlineLabeled>>> issueTypeMapEntryList;
    private Map<String, List<MayoralHotlineLabeled>> expressBrandMap;
    private Map<String, List<MayoralHotlineLabeled>> issueTypeMap;

    public Analysis build(List<MayoralHotlineLabeled> list) {
        Map<String, List<MayoralHotlineLabeled>> unsortedExpressBrandMap = new HashMap<>();
        Map<String, List<MayoralHotlineLabeled>> unsortedIssueTypeMap = new HashMap<>();
        for (ExpressBrand expressBrand : expressBrandDao.findAll()) {
            unsortedExpressBrandMap.put(expressBrand.getName(), new ArrayList<>());
        }
        for (IssueType issueType : issueTypeDao.findAll()) {
            unsortedIssueTypeMap.put(issueType.getName(), new ArrayList<>());
        }
        list.removeIf(ele -> ele.getExpressBrand().getName().equals("{{多家}}"));
        for (var labeled : list) {
            String expressBrandName = labeled.getExpressBrand().getName();
            String issueTypeName = labeled.getIssueType().getName();
            if (unsortedExpressBrandMap.containsKey(expressBrandName)) {
                unsortedExpressBrandMap.get(expressBrandName).add(labeled);
            }
            if (unsortedIssueTypeMap.containsKey(issueTypeName)) {
                unsortedIssueTypeMap.get(issueTypeName).add(labeled);
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

    private List<Map.Entry<String,List<MayoralHotlineLabeled>>> sort(Map<String,List<MayoralHotlineLabeled>> map){
        return map.entrySet().stream().sorted((o1, o2) -> o2.getValue().size() - o1.getValue().size()).toList();
    }

    private Map<String,List<MayoralHotlineLabeled>> descSort(Map<String,List<MayoralHotlineLabeled>> map){
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

    public Map<String, List<MayoralHotlineLabeled>> getExpressBrandMap() {
        return expressBrandMap;
    }

    public Map<String, List<MayoralHotlineLabeled>> getIssueTypeMap() {
        return issueTypeMap;
    }

    public List<Map.Entry<String, List<MayoralHotlineLabeled>>> getExpressBrandMapEntryList() {
        return expressBrandMapEntryList;
    }

    public List<Map.Entry<String, List<MayoralHotlineLabeled>>> getIssueTypeMapEntryList() {
        return issueTypeMapEntryList;
    }
}
