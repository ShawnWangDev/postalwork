package pers.wangsc.postalwork.service;

import pers.wangsc.postalwork.dao.IssueTypeDao;
import pers.wangsc.postalwork.entity.IssueType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueTypeService {
    @Autowired
    private IssueTypeDao issueTypeDao;

    public List<IssueType> findAll(){
        return issueTypeDao.findAll();
    }
}
