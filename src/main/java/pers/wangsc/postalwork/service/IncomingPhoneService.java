package pers.wangsc.postalwork.service;

import pers.wangsc.postalwork.dao.IncomingPhoneDao;
import pers.wangsc.postalwork.entity.IncomingPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomingPhoneService {
    @Autowired
    IncomingPhoneDao incomingPhoneDao;

    public List<IncomingPhone> findAll(){
        return incomingPhoneDao.findAll();
    }

    public IncomingPhone save(IncomingPhone incomingPhone){
       return incomingPhoneDao.save(incomingPhone);
    }
}
