package com.cred0.picturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cred0.picturebackend.model.dto.space.SpaceQueryRequest;
import com.cred0.picturebackend.model.dto.space.SpaceAddRequest;
import com.cred0.picturebackend.model.entity.Space;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cred0.picturebackend.model.entity.User;
import com.cred0.picturebackend.model.vo.SpaceVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 空间服务接口，提供空间相关的数据库操作和业务逻辑
 * @author Cred0
 * @description 针对表【space(空间)】的数据库操作Service
 * @createDate 2025-02-26 15:54:57
 */
public interface SpaceService extends IService<Space> {

    /**
     * 构建空间查询条件包装器
     * @param spaceQueryRequest 空间查询请求参数DTO
     * @return 组装好的查询条件包装器对象
     */
    QueryWrapper<Space> getQueryWrapper(SpaceQueryRequest spaceQueryRequest);

    /**
     * 将Space实体转换为VO视图对象（包含权限处理等）
     * @param space 空间实体对象
     * @param request HTTP请求对象（用于获取当前用户信息）
     * @return 空间视图对象VO
     */
    SpaceVO getSpaceVO(Space space, HttpServletRequest request);

    /**
     * 分页转换方法：将Space实体分页转换为VO分页
     * @param spacePage 原始空间实体分页数据
     * @param request HTTP请求对象（用于权限校验等）
     * @return 转换后的VO分页数据
     */
    Page<SpaceVO> getSpaceVOPage(Page<Space> spacePage, HttpServletRequest request);

    /**
     * 空间数据校验方法
     * @param space 待校验的空间对象
     * @param add 是否为新增操作标识
     * @throws IllegalArgumentException 当校验不通过时抛出
     */
    void validSpace(Space space, boolean add);

    /**
     * 根据空间层级自动填充相关字段
     * （如路径、权限级别等层级相关信息）
     * @param space 需要填充的空间对象
     */
    void fillSpaceBySpaceLevel(Space space);

    /**
     * 创建空间核心方法
     * @param spaceAddRequest 空间创建请求参数DTO
     * @param loginUser 当前登录用户对象
     * @return 新创建的空间ID
     * @throws IllegalArgumentException 当参数校验失败时抛出
     */
    long addSpace(SpaceAddRequest spaceAddRequest, User loginUser);
}
