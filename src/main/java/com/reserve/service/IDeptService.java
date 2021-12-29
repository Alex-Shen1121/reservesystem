package com.reserve.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.reserve.model.Dept;
import com.reserve.model.Node;

import java.util.List;

/**
 * <p>
 * 科室 服务类
 * </p>
 */
public interface IDeptService extends IService<Dept> {


    PageInfo<Dept> queryDeptAll(int page,int pageSize,Dept dept);

    List<Dept> queryListByParentId(Long parentId);

    /**
     * 查询科室分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Dept>
     */
    IPage<Dept> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加科室
     *
     * @param dept 科室
     * @return int
     */
    int add(Dept dept);

    /**
     * 删除科室
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改科室
     *
     * @param dept 科室
     * @return int
     */
    int updateData(Dept dept);

    /**
     * id查询数据
     *
     * @param id id
     * @return Dept
     */
    Dept findById(Long id);

    /**
     * 查询树结构
     */
     List<Node> queryDeptTree();


}
