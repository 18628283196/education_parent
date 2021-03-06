package com.atguigu.guli.service.edu.controller.admin;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-07-22
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public R getList(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list);
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "根据id删除",notes = "根据id删除,逻辑删除")
    public R deleteById(@ApiParam(value = "讲师id",required = true) @PathVariable Integer id){
        boolean result = teacherService.removeById(id);
        if (result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }

    }

    @GetMapping("/list/{page}/{limit}")
    @ApiOperation(value = "讲师分页查询",notes = "可以根据条件进行分页查询")
    public R listPage(@ApiParam(value = "当前页数",required = true) @PathVariable Long page,
                      @ApiParam(value = "每页条数",required = true) @PathVariable Long limit,
                      @ApiParam("讲师分页查询对象")TeacherQueryVo teacherQueryVo){
        Page<Teacher> teacherPage = new Page<>(page, limit);
        //可以值传一个参数:Page<Teacher> pageModel = teacherService.page(teacherPage);
        Page<Teacher> pageModel = teacherService.selectPage(teacherPage,teacherQueryVo);

        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();

        return R.ok().data("total",total).data("rows",records);
    }

    @PostMapping("save")
    @ApiOperation("新增讲师")
    public R saveTeacher(@ApiParam(value = "讲师对象",required = true) @RequestBody Teacher teacher){
        boolean b = teacherService.save(teacher);
        if (b){
            return R.ok().message("保存成功");
        }else{
            return R.error().message("保存失败");
        }
    }

    @PutMapping("update")
    @ApiOperation("根据id更新")
    public R updateTeacher(@ApiParam(value = "讲师对象",required = true)@RequestBody Teacher teacher){
        boolean b = teacherService.updateById(teacher);
        if (b){
            return R.ok().message("更新成功");
        }else{
            return R.error().message("更新失败");
        }
    }

    @PutMapping("/getTeacherById/{id}")
    @ApiOperation("根据id获取讲师对象")
    public R getTeacherById(@ApiParam(value = "讲师id",required = true)@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @PutMapping("updateBycondition")
    @ApiOperation("根据条件更新")
    public R updateTeacherBycondition(@ApiParam(value = "讲师对象",required = true)@RequestBody Teacher teacher){
        boolean b = teacherService.updateTeacherBycondition(teacher);
        if (b){
            return R.ok().message("更新成功");
        }else{
            return R.error().message("更新失败");
        }
    }

    @PutMapping("deleteBycondition")
    @ApiOperation("根据条件删除")
    public R deleteTeacherBycondition(@ApiParam(value = "讲师对象",required = true)@RequestBody Teacher teacher){
        boolean b = teacherService.deleteTeacherBycondition(teacher);
        if (b){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("删除失败");
        }
    }

}

