package com.itheima.service.impl;

import com.itheima.domain.City;
import com.itheima.mapper.CityMapper;
import com.itheima.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CityServiceImpl implements CityService{
    @Autowired
    private CityMapper cityMapper;
    public List<City> findAll() {
        return cityMapper.findAll();
    }
}
