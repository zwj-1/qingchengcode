package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandMapper;
import com.qingcheng.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    public BrandMapper brandMapper;

    //查询全部
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    //分页查询全部
    @Override
    public List<Brand> findAllByPage(int page, int size) {
        PageHelper.startPage(page, size);
        return brandMapper.selectAll();
    }

    //搜索条件查询全部
    @Override
    public List<Brand> findAllBySearch(Map<String, Object> searchMap) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + (String) searchMap.get("name") + "%");
            }
            if (searchMap.get("letter") != null && !"".equals(searchMap.get("letter"))) {
                criteria.andLike("letter", "%" + (String) searchMap.get("letter") + "%");
            }
        }
        List<Brand> brandList = brandMapper.selectByExample(example);
        return brandList;
    }

    //分页搜索条件查询全部
    @Override
    public List findAllBySearchAndPage(Map searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + (String) searchMap.get("name") + "%");
            }
            if (searchMap.get("letter") != null && !"".equals(searchMap.get("letter"))) {
                criteria.andLike("letter", "%" + (String) searchMap.get("letter") + "%");
            }
        }
        return brandMapper.selectByExample(example);
    }

    //根据ID查询
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    //新增品牌
    @Override
    public void save(Brand brand) {
        brandMapper.insert(brand);
    }

    //修改品牌
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    //删除品牌
    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }
}
