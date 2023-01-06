package pers.wangsc.postalwork.dao;

import org.springframework.stereotype.Repository;
import pers.wangsc.postalwork.entity.ExpressBrand;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ExpressBrandDao extends JpaRepository<ExpressBrand, Integer> {

}
