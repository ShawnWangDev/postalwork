package pers.wangsc.postalwork.dao;

import pers.wangsc.postalwork.entity.IncomingPhone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomingPhoneDao extends JpaRepository<IncomingPhone, Integer> {

}
