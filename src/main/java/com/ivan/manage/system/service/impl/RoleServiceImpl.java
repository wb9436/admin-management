package com.ivan.manage.system.service.impl;

import com.ivan.manage.system.dao.RoleDao;
import com.ivan.manage.system.dao.RoleMenuDao;
import com.ivan.manage.system.dao.UserRoleDao;
import com.ivan.manage.system.dto.RoleDto;
import com.ivan.manage.system.dto.RoleMenuDto;
import com.ivan.manage.system.dto.UserRoleDto;
import com.ivan.manage.system.service.IRoleService;
import com.ivan.manage.utils.BService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Service
public class RoleServiceImpl extends BService implements IRoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<RoleDto> list() {
        List<RoleDto> roles = roleDao.list(new HashMap<>(16));
        return roles;
    }

    @Transactional
    @Override
    public int save(RoleDto role) {
        int count = roleDao.save(role);
        List<Integer> menuIds = role.getMenuIds();
        Integer roleId = role.getRoleId();
        List<RoleMenuDto> rms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            RoleMenuDto rmDo = new RoleMenuDto();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuDao.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuDao.batchSave(rms);
        }
        return count;
    }

    @Transactional
    @Override
    public int remove(Integer id) {
        int count = roleDao.remove(id);
        userRoleDao.removeByRoleId(id);
        roleMenuDao.removeByRoleId(id);
        return count;
    }

    @Override
    public RoleDto get(Integer id) {
        RoleDto roleDto = roleDao.get(id);
        return roleDto;
    }

    @Transactional
    @Override
    public int update(RoleDto role) {
        int r = roleDao.update(role);
        List<Integer> menuIds = role.getMenuIds();
        Integer roleId = role.getRoleId();
        r += roleMenuDao.removeByRoleId(roleId);
        List<RoleMenuDto> rms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            RoleMenuDto rmDo = new RoleMenuDto();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            r += roleMenuDao.batchSave(rms);
        }
        return r;
    }

    @Override
    public int batchremove(Integer[] ids) {
        int r = roleDao.batchRemove(ids);
        return r;
    }

    @Override
    public List<RoleDto> getByUserId(Integer userId) {
        UserRoleDto userRoleDto = userRoleDao.getByUserId(userId);
        List<RoleDto> roles = roleDao.list(new HashMap<>(16));
        for (RoleDto roleDto : roles) {
            roleDto.setRoleSign("false");
            if (Objects.equals(roleDto.getRoleId(), userRoleDto.getRoleId())) {
                roleDto.setRoleSign("true");
                break;
            }
        }
        return roles;
    }

}
