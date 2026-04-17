package com.example.spba.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spba.domain.entity.Member;
import com.example.spba.domain.entity.MemberInfo;
import com.example.spba.domain.entity.LoginLog;
import com.example.spba.domain.entity.Role;
import com.example.spba.dao.MemberMapper;
import com.example.spba.service.MemberService;
import com.example.spba.service.MemberInfoService;
import com.example.spba.service.LoginLogService;
import com.example.spba.service.MenuService;
import com.example.spba.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService
{

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private MemberInfoService memberInfoService;

    @Override
    public HashMap checkLogin(HashMap params)
    {
        HashMap result = new HashMap();
        result.put("status", false);

        HashMap member = this.baseMapper.getInfo(params);
        if (member == null || member.get("status") == null || member.get("status").equals(0)) {
            result.put("message", "登录失败");
            return result;
        }
        if (!DigestUtils.md5DigestAsHex((params.get("password") + member.get("safe").toString()).getBytes()).equals(member.get("password"))) {
            result.put("message", "密码错误");
            return result;
        }

        HashMap<String, Object> where = new HashMap<>();
        where.put("status", 1);
        Object roleIdsObj = member.get("role_ids");
        if (roleIdsObj != null) {
            try {
                where.put("role_ids", JSONUtil.parse(roleIdsObj.toString()).toBean(List.class));
            } catch (Exception e) {
                result.put("message", "登录失败");
                return result;
            }
        }
        List<Role> roles = roleService.getAll(where);
        if (roles.size() == 0) {
            result.put("message", "登录失败");
            return result;
        }

        // 登录
        StpUtil.login(member.get("id"));

        // 更新登录信息
        updateLogin(Long.valueOf(member.get("id").toString()), params.get("ip").toString());

        HashMap data = new HashMap<>();
        data.put("id", member.get("id"));
        data.put("username", member.get("username"));
        data.put("token", StpUtil.getTokenValue());
        if (member.get("avatar") != null && !member.get("avatar").toString().isEmpty()) {
            data.put("avatar", member.get("avatar"));
        }

        try {
            Long memberId = Long.valueOf(member.get("id").toString());
            MemberInfo memberInfo = memberInfoService.getOne(
                new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<MemberInfo>().eq("member_id", memberId)
            );
            if (memberInfo != null) {
                if (memberInfo.getNick_name() != null && !memberInfo.getNick_name().isEmpty()) {
                    data.put("nick_name", memberInfo.getNick_name());
                }
                if (memberInfo.getAvatar_url() != null && !memberInfo.getAvatar_url().isEmpty()) {
                    data.put("avatar", memberInfo.getAvatar_url());
                }
            }
        } catch (Exception ignored) {}

        result.put("data", data);
        result.put("status", true);

        return result;
    }

    @Override
    public Page<HashMap> getList(Page page, HashMap params) {
        return this.baseMapper.getList(page, params);
    }

    @Override
    public HashMap getInfo(HashMap params) {
        return this.baseMapper.getInfo(params);
    }

    @Override
    public Boolean checkUsername(@NotBlank String username) {
        HashMap<String, Object> where = new HashMap<>();
        where.put("username", username);
        return getInfo(where) != null;
    }

    @Override
    public List<HashMap> getRoleAdminAll(Integer roleId) {
        return this.baseMapper.getRoleAdminAll(roleId);
    }

    @Override
    public List<HashMap> getPermissionList(Integer adminId)
    {
        List<HashMap> list = new ArrayList<>();
        Member member = this.getById(adminId);

        HashMap<String, Object> where = new HashMap<>();
        where.put("status", 1);
        where.put("role_ids", JSONUtil.parse(member.getRole_ids()).toBean(List.class));
        List<Role> roles = roleService.getAll(where);

        Integer root = 0;
        List<Integer> menuIds = new ArrayList<>();
        for (Role role : roles) {
            if (role.getRoot().equals(1)) {
                root = 1;
                break;
            }
            for (Object id : JSONUtil.parse(role.getPermission()).toBean(List.class)) {
                menuIds.add(Integer.valueOf(id.toString()));
            }
        }
        if (root.equals(0) && menuIds.size() == 0) {
            return list;
        }

        HashMap<String, Object> query = new HashMap<String, Object>();
        query.put("status", 1);
        if (root.equals(0)) {
            query.put("menu_ids", menuIds);
        }
        List<HashMap> menus = menuService.getAll(query);

        return menus;
    }

    private void updateLogin(Long id, String ip)
    {
        HashMap<String, Object> updateParams = new HashMap<>();
        updateParams.put("id", id);
        updateParams.put("login_ip", ip);
        updateParams.put("login_time", new Date());
        this.baseMapper.updateLogin(updateParams);

        LoginLog log = new LoginLog();
        log.setAdminId(Integer.valueOf(id.toString()));
        log.setLoginIp(ip);
        loginLogService.save(log);
    }
}