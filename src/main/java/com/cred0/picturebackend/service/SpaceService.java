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
* @author Cred0
* @description 针对表【space(空间)】的数据库操作Service
* @createDate 2025-02-26 15:54:57
*/
public interface SpaceService extends IService<Space> {

    QueryWrapper<Space> getQueryWrapper(SpaceQueryRequest spaceQueryRequest);

    SpaceVO getSpaceVO(Space space, HttpServletRequest request);

    Page<SpaceVO> getSpaceVOPage(Page<Space> spacePage, HttpServletRequest request);
    
    void validSpace(Space space, boolean add);

    void fillSpaceBySpaceLevel(Space space);

    long addSpace(SpaceAddRequest spaceAddRequest, User loginUser);
}
