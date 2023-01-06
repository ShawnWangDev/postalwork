package pers.wangsc.postalwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.wangsc.postalwork.entity.IssueCondition;

@Repository
public interface IssueConditionDao extends JpaRepository<IssueCondition,Integer> {

}
