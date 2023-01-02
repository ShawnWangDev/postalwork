package pers.wangsc.postalwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pers.wangsc.postalwork.entity.MayoralHotlineLabeled;

import java.util.List;

public interface MayoralHotlineLabeledDao extends JpaRepository<MayoralHotlineLabeled,Integer> {
    @Query(value="select mayoral_hotline_id from mayoral_hotline_labeled",nativeQuery = true)
    List<Integer> findAllMayoralHotlineId();
    MayoralHotlineLabeled findByMayoralHotlineId(Integer id);
}
