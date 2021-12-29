package com.reserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reserve.model.Dept;
import com.reserve.model.Node;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 科室 Mapper 接口
 * </p>
 */
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 查询所有的记录 带条件
     */
    List<Dept> queryDeptAll(Dept dept);


   //根据父节点查询list集合
    List<Dept> queryListByParentId(@Param("parentId") Long parentId);


    /**
     * 查询部门树
     */
    List<Node> queryDeptTree();



}
