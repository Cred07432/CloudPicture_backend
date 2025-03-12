package com.cred0.picturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cred0.picturebackend.api.aliyunai.model.CreateOutPaintingTaskResponse;
import com.cred0.picturebackend.model.dto.picture.*;
import com.cred0.picturebackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cred0.picturebackend.model.entity.User;
import com.cred0.picturebackend.model.vo.PictureVO;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;

/**
 * 图片服务接口
 * @author Cred0
 * @description 针对表【picture(图片)】的数据库操作Service
 * @createDate 2025-02-24 13:14:51
 * <p>
 * 本服务包含图片的完整生命周期管理，包括：
 * - 图片上传（单文件/批量）
 * - 图片查询与分页
 * - 图片审核流程
 * - 图片编辑与删除
 * - 文件资源清理
 * - 权限验证等功能
 * </p>
 */
public interface PictureService extends IService<Picture> {

    /**
     * 单图片上传
     * @param inputSource 图片输入源（可以是MultipartFile文件对象或URL字符串）
     * @param pictureUploadRequest 图片上传请求DTO，包含图片元数据
     * @param loginUser 当前登录用户
     * @return 图片视图对象（包含访问URL等展示信息）
     */
    PictureVO uploadPicture(Object inputSource, PictureUploadRequest pictureUploadRequest, User loginUser);

    /**
     * 构建动态查询条件
     * @param pictureQueryRequest 图片查询请求参数
     * @return 封装好的MyBatis-Plus查询条件构造器
     */
    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

    /**
     * 将图片实体转换为视图对象
     * @param picture 图片实体对象
     * @param request HTTP请求对象（用于生成完整图片URL）
     * @return 图片视图对象
     */
    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    /**
     * 分页转换方法
     * @param picturePage 图片实体分页结果
     * @param request HTTP请求对象
     * @return 图片视图对象分页结果
     */
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    /**
     * 校验图片参数合法性
     * @param picture 待校验的图片实体
     * @throws IllegalArgumentException 当参数不符合要求时抛出
     */
    void validPicture(Picture picture);

    /**
     * 执行图片审核操作
     * @param pictureReviewRequest 图片审核请求参数
     * @param loginUser 执行审核操作的用户
     * @throws IllegalStateException 当图片不处于可审核状态时抛出
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);

    /**
     * 填充审核相关参数
     * @param picture 图片实体对象
     * @param loginUser 审核操作人
     */
    void fillReviewParams(Picture picture, User loginUser);

    /**
     * 批量上传图片（如通过多个URL进行抓取）
     * @param pictureUploadByBatchRequest 批量上传请求参数
     * @param loginUser 操作人
     * @return 成功创建的有效图片数量
     */
    Integer uploadPictureByBatch(
            PictureUploadByBatchRequest pictureUploadByBatchRequest,
            User loginUser
    );

    /**
     * 异步清理图片物理文件
     * @param oldPicture 需要清理的图片实体（通常用于更新操作时清理旧文件）
     */
    @Async
    void clearPictureFile(Picture oldPicture);

    /**
     * 删除图片（逻辑删除）
     * @param pictureId 要删除的图片ID
     * @param loginUser 操作人（用于权限验证）
     * @throws SecurityException 当无操作权限时抛出
     */
    void deletePicture(long pictureId, User loginUser);

    /**
     * 编辑图片元数据
     * @param pictureEditRequest 图片编辑请求参数
     * @param loginUser 操作人
     * @throws SecurityException 当无编辑权限时抛出
     */
    void editPicture(PictureEditRequest pictureEditRequest, User loginUser);

    /**
     * 验证图片操作权限
     * @param loginUser 当前登录用户
     * @param picture 目标图片实体
     * @throws SecurityException 当用户无权限操作该图片时抛出
     */
    void checkPictureAuth(User loginUser, Picture picture);

    CreateOutPaintingTaskResponse createPictureOutPaintingTask(CreatePictureOutPaintingTaskRequest createPictureOutPaintingTaskRequest, User loginUser);
}
