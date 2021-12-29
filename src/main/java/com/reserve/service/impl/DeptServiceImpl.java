package com.reserve.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reserve.dao.DeptMapper;
import com.reserve.model.Dept;
import com.reserve.model.Node;
import com.reserve.service.IDeptService;
import com.reserve.util.TreeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 科室 服务实现类
 * </p>
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Resource
    private DeptMapper deptMapper;
    @Override
    public PageInfo<Dept> queryDeptAll(int page,int pageSize,Dept dept) {
        PageHelper.startPage(page,pageSize);
        List<Dept> list=deptMapper.queryDeptAll(dept);
        PageInfo<Dept> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public List<Dept> queryListByParentId(Long parentId) {
        return deptMapper.queryListByParentId(parentId);
    }

    @Override
    public IPage<Dept> findListByPage(Integer page, Integer pageCount){
        IPage<Dept> wherePage = new Page<>(page, pageCount);
        Dept where = new Dept();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Dept dept){
        return baseMapper.insert(dept);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Dept dept){
        return baseMapper.updateById(dept);
    }

    @Override
    public Dept findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public List<Node> queryDeptTree() {
        List nodeList=deptMapper.queryDeptTree();
        //创建TreeUtil
        TreeUtil treeUtil=new TreeUtil();
        List<Node> list= treeUtil.build(nodeList);
        return list;
    }

}
