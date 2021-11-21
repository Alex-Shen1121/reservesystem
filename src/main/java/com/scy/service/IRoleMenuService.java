package com.scy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.model.RoleMenu;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 查询角色菜单关联表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<RoleMenu>
     */
    IPage<RoleMenu> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加角色菜单关联表
     *
     * @param roleMenu 角色菜单关联表
     * @return int
     */
    int add(RoleMenu roleMenu);

    /**
     * 删除角色菜单关联表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改角色菜单关联表
     *
     * @param roleMenu 角色菜单关联表
     * @return int
     */
    int updateData(RoleMenu roleMenu);

    /**
     * id查询数据
     *
     * @param id id
     * @return RoleMenu
     */
    RoleMenu findById(Long id);
}
