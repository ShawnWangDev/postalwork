package pers.wangsc.postalwork.dao;

import org.springframework.stereotype.Repository;
import pers.wangsc.postalwork.entity.IncomingPhone;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IncomingPhoneDao extends JpaRepository<IncomingPhone, Integer> {

}
