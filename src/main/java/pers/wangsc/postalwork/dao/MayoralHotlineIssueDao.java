package pers.wangsc.postalwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pers.wangsc.postalwork.entity.MayoralHotline;
import pers.wangsc.postalwork.entity.MayoralHotlineIssue;

import java.util.Date;
import java.util.List;

@Repository
public interface MayoralHotlineIssueDao extends JpaRepository<MayoralHotlineIssue,Integer> {
    @Query(value="select mayoral_hotline_id from mayoral_hotline_issue",nativeQuery = true)
    List<Integer> findAllMayoralHotlineId();
    @Query("select issue from MayoralHotlineIssue issue where " +
            "issue.mayoralHotline.appealDateTime >= :startDate " +
            "and issue.mayoralHotline.appealDateTime <= :endDate order by issue.mayoralHotline.appealDateTime desc")
    List<MayoralHotlineIssue> findAllByAppealDateTimeBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    MayoralHotlineIssue findByMayoralHotlineId(Integer id);

    @Query("select issue from MayoralHotlineIssue issue where issue.issueCondition.id=:id and issue.mayoralHotline.appealDateTime>=:date")
    List<MayoralHotlineIssue> findByIssueConditionId(@Param("id") Integer id,@Param("date") Date date);
}
