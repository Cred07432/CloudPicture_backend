package com.cred0.picturebackend.service;

import com.cred0.picturebackend.model.dto.space.SpaceAddRequest;
import com.cred0.picturebackend.model.entity.Space;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cred0.picturebackend.model.entity.User;

/**
* @author Cred0
* @description 针对表【space(空间)】的数据库操作Service
* @createDate 2025-02-26 15:54:57
*/
public interface SpaceService extends IService<Space> {

    void validSpace(Space space, boolean add);

    void fillSpaceBySpaceLevel(Space space);

    long addSpace(SpaceAddRequest spaceAddRequest, User loginUser);
}
