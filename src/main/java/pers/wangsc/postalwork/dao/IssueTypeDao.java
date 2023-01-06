package pers.wangsc.postalwork.dao;

import org.springframework.stereotype.Repository;
import pers.wangsc.postalwork.entity.IssueType;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IssueTypeDao extends JpaRepository<IssueType, Integer> {

}
