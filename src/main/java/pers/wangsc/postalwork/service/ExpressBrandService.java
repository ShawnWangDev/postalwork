package pers.wangsc.postalwork.service;

import pers.wangsc.postalwork.dao.ExpressCompanyDao;
import pers.wangsc.postalwork.entity.ExpressBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressBrandService {
    @Autowired
    private ExpressCompanyDao expressCompanyDao;

    public List<ExpressBrand> findAll(){
        return expressCompanyDao.findAll();
    }
}
