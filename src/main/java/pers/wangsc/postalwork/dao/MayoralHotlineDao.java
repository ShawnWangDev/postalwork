package pers.wangsc.postalwork.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pers.wangsc.postalwork.entity.MayoralHotline;

import java.util.List;

public interface MayoralHotlineDao extends JpaRepository<MayoralHotline, Integer> {
    @Query(value = "select m.file_name from mayoral_hotline m", nativeQuery = true)
    List<String> findAllFileName();
    Page<MayoralHotline> findAllByOrderByDeadlineDateTimeDesc(Pageable pageable);
    Page<MayoralHotline> findByOrderByDeadlineDateTimeDesc(Pageable pageable);
}
