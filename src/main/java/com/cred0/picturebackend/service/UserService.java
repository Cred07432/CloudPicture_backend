package com.cred0.picturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cred0.picturebackend.model.dto.user.UserQueryRequest;
import com.cred0.picturebackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cred0.picturebackend.model.vo.LoginUserVO;
import com.cred0.picturebackend.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户服务接口，提供用户相关的数据库操作和业务逻辑
 * 针对表【user(用户)】的数据库操作Service
 *
 * @author Cred0
 * @createDate 2025-02-23 13:14:36
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册方法
     * <p>
     * 注册新用户，包含账户校验、密码一致性验证、密码加密以及将用户数据保存到数据库中，
     * 成功后返回新注册用户的ID。
     * </p>
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码，验证两次密码输入是否一致
     * @return 新用户ID
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录方法
     * <p>
     * 处理用户登录请求，验证用户账户与密码，
     * 登录成功后返回脱敏处理后的用户登录信息，并维护用户会话状态。
     * </p>
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request      HTTP请求对象（用于获取请求信息及会话管理）
     * @return 脱敏后的用户登录信息(LoginUserVO)
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     * <p>
     * 从HTTP请求中提取当前登录用户信息，并返回对应的完整用户实体对象，
     * 注意该对象可能包含敏感信息。
     * </p>
     *
     * @param request HTTP请求对象
     * @return 当前登录的用户实体(User)
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销方法
     * <p>
     * 处理用户注销操作，清除会话或其他登录状态信息，实现用户退出登录。
     * </p>
     *
     * @param request HTTP请求对象
     * @return 注销是否成功（true表示成功，false表示失败）
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 将用户实体转换为用户视图对象
     * <p>
     * 将完整的用户实体(User)转换为适合展示的用户视图对象(UserVO)，
     * 在转换过程中会对敏感信息进行脱敏处理。
     * </p>
     *
     * @param user 用户实体对象
     * @return 转换后的用户视图对象(UserVO)
     */
    UserVO getUserVO(User user);

    /**
     * 批量将用户实体转换为用户视图对象列表
     * <p>
     * 对多个用户实体进行转换操作，生成对应的用户视图对象列表，
     * 同时确保敏感数据得到妥善脱敏。
     * </p>
     *
     * @param userList 用户实体列表
     * @return 转换后的用户视图对象列表(List<UserVO>)
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 获取脱敏的已登录用户视图信息
     * <p>
     * 将包含敏感信息的用户实体转换为脱敏后的登录用户视图对象，
     * 适用于前端展示或其它业务逻辑场景。
     * </p>
     *
     * @param user 用户实体对象
     * @return 脱敏后的登录用户视图对象(LoginUserVO)
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 对用户密码进行加密处理
     * <p>
     * 根据安全要求对用户密码进行加密，返回加密后的密码字符串，
     * 以用于存储或后续的密码比对。
     * </p>
     *
     * @param userPassword 用户原始密码
     * @return 加密后的密码字符串
     */
    String getEncryptPassword(String userPassword);

    /**
     * 构造用户查询条件包装器
     * <p>
     * 根据用户查询请求参数构造查询条件包装器(QueryWrapper)，
     * 用于灵活地查询用户数据。
     * </p>
     *
     * @param userQueryRequest 用户查询请求参数DTO
     * @return 组装好的查询条件包装器(QueryWrapper<User>)
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    /**
     * 判断用户是否为管理员
     * <p>
     * 根据用户角色或权限信息判断当前用户是否具有管理员权限，
     * 返回相应的布尔值。
     * </p>
     *
     * @param user 用户实体对象
     * @return 如果用户为管理员返回true，否则返回false
     */
    boolean isAdmin(User user);
}
