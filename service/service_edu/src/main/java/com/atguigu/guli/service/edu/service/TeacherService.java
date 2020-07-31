package com.atguigu.guli.service.edu.service;

import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-07-22
 */
public interface TeacherService extends IService<Teacher> {

    Page<Teacher> selectPage(Page<Teacher> teacherPage, TeacherQueryVo teacherQueryVo);
}
