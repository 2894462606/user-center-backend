package com.pikachu.usercenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pikachu.usercenter.model.dto.request.user.UserUpdateRequest;
import com.pikachu.usercenter.model.entity.User;
import com.pikachu.usercenter.model.vo.LoginUserVO;
import com.pikachu.usercenter.model.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 28944
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2023-12-03 17:15:34
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @return 新用户 id
     */
    @Transactional
    Long userRegister(String account, String password);

    /**
     * 用户登录
     *
     * @param account  账户
     * @param password 密码
     * @return 登录用户视图对象
     */
    LoginUserVO userLogin(String account, String password, HttpServletRequest request);

    /**
     * 获取当前登录的用户
     *
     * @param request
     * @return 登录用户视图对象
     */
    LoginUserVO getCurrentLoginUser(HttpServletRequest request);


    /**
     * 用户注销
     *
     * @param request
     */
    void userLogout(HttpServletRequest request);

    /**
     * 搜索用户列表（普通用户操作）
     *
     * @param conditions 搜索条件
     * @param current    页码
     * @param pageSize   每页条数
     * @return 搜索到的用户列表
     */
    IPage<UserVO> searchUsers(Map<String, Object> conditions, Long current, Long pageSize);

    /**
     * 根据 id 获取用户信息（脱敏）
     *
     * @param id 用户 id
     * @return 脱敏信息后的用户对象
     */
    UserVO getUserVOById(Long id);

    /**
     * 查询用户列表
     * 返回UserVO
     *
     * @param queryWrapper
     * @return
     */
    List<UserVO> listUserVO(QueryWrapper<User> queryWrapper);

    /**
     * 更新用户信息
     *
     * @param userUpdateRequest
     * @param request
     */
    @Transactional
    void updateUser(UserUpdateRequest userUpdateRequest, HttpServletRequest request);

    /**
     * 匹配用户
     *
     * @param num     匹配出的用户个数
     * @param request
     * @return
     */
    List<UserVO> matchUsers(Integer num, HttpServletRequest request);

}
