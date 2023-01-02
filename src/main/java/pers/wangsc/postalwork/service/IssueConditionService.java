package pers.wangsc.postalwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.wangsc.postalwork.dao.IssueConditionDao;
import pers.wangsc.postalwork.entity.IssueCondition;

import java.util.List;

@Service
public class IssueConditionService {
    @Autowired
    private IssueConditionDao issueConditionDao;

    public List<IssueCondition> findAll(){
        return issueConditionDao.findAll();
    }
}
