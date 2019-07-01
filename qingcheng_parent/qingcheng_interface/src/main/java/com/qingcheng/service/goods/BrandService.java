package com.qingcheng.service.goods;

import com.qingcheng.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    public List<Brand> findAll();

    public List<Brand> findAllByPage(int page,int size);

    public List<Brand> findAllBySearch(Map<String,Object> searchMap);

    List findAllBySearchAndPage(Map searchMap, int page, int size);

    Brand findById(Integer id);

    void save(Brand brand);

    void update(Brand brand);

    void delete(Integer id);
}
