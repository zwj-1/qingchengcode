package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import entry.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    //查询全部
    @RequestMapping("/findAll")
    public List<Brand> findAll() {
        return brandService.findAll();
    }

    //分页查询全部
    @RequestMapping("/findAllByPage")
    public PageInfo<Brand> findAllByPage(int page, int size) {

        List<Brand> brandList = brandService.findAllByPage(page, size);
        PageInfo<Brand> pageInfo = new PageInfo<Brand>(brandList);
        return pageInfo;
    }

    //搜索条件查询全部
    @PostMapping("/findAllBySearch")
    public List<Brand> findAllBySearch(@RequestBody Map searchMap) {
        List brandList = brandService.findAllBySearch(searchMap);
        return brandList;
    }

    //分页搜索条件查询全部
    @PostMapping("/findAllBySearchAndPage")
    public PageInfo<Brand> findAllBySearchAndPage(@RequestBody Map searchMap, int page, int size) {
        List brandList = brandService.findAllBySearchAndPage(searchMap, page, size);
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        return pageInfo;
    }

    //根据ID查询
    @RequestMapping("/findById")
    public Brand findById(Integer id) {
        return brandService.findById(id);
    }

    //新增品牌
    @RequestMapping("/save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return new Result();
    }

    //修改品牌
    @RequestMapping("/update")
    public Result update(@RequestBody Brand brand) {
        //int i = 1 / 0;
        brandService.update(brand);
        return new Result();
    }

    //删除品牌
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        brandService.delete(id);
        return new Result();
    }
}
