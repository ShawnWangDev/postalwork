package pers.wangsc.postalwork.dao;

import pers.wangsc.postalwork.entity.IssueType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueTypeDao extends JpaRepository<IssueType, Integer> {

}
