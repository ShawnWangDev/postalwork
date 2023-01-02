package pers.wangsc.postalwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.wangsc.postalwork.entity.IssueCondition;

public interface IssueConditionDao extends JpaRepository<IssueCondition,Integer> {

}
