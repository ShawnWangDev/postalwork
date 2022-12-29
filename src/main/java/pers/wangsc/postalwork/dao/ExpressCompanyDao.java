package pers.wangsc.postalwork.dao;

import pers.wangsc.postalwork.entity.ExpressBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpressCompanyDao extends JpaRepository<ExpressBrand, Integer> {

}
