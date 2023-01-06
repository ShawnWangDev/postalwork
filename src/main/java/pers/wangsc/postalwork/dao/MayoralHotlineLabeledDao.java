package pers.wangsc.postalwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pers.wangsc.postalwork.entity.MayoralHotlineLabeled;

import java.util.Date;
import java.util.List;

@Repository
public interface MayoralHotlineLabeledDao extends JpaRepository<MayoralHotlineLabeled,Integer> {
    @Query(value="select mayoral_hotline_id from mayoral_hotline_labeled",nativeQuery = true)
    List<Integer> findAllMayoralHotlineId();
    @Query("select labeled from MayoralHotlineLabeled labeled where " +
            "labeled.mayoralHotline.appealDateTime >= :startDate " +
            "and labeled.mayoralHotline.appealDateTime <= :endDate order by labeled.mayoralHotline.appealDateTime desc")
    List<MayoralHotlineLabeled> findAllByAppealDateTimeBetween(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
    MayoralHotlineLabeled findByMayoralHotlineId(Integer id);
}
