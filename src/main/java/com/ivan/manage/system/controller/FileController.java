package com.ivan.manage.system.controller;

import com.ivan.manage.configuration.annotation.Log;
import com.ivan.manage.configuration.controller.BaseController;
import com.ivan.manage.configuration.util.PageUtils;
import com.ivan.manage.configuration.util.Result;
import com.ivan.manage.system.dto.FileDto;
import com.ivan.manage.system.service.IFileService;
import com.ivan.manage.utils.Query;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequestMapping("/sys/file")
@Controller
public class FileController extends BaseController {
    public String prefix = "system/file";

    @Autowired
    private IFileService fileService;

    @RequiresPermissions("sys:file:list")
    @GetMapping()
    protected String toList() {
        return prefix + "/list";
    }

    @RequiresPermissions("sys:file:list")
    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<FileDto> list = fileService.list(query);
        int total = fileService.count(query);
        PageUtils pageUtils = new PageUtils(list, total);
        return pageUtils;
    }

    @Log("上传文件")
    @RequiresPermissions("sys:file:add")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }


    @Log("保存文件")
    @RequiresPermissions("sys:file:add")
    @ResponseBody
    @PostMapping("/save")
    public Result save(FileDto fileDto, MultipartFile file) {
        if (fileService.save(fileDto, file) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Log("删除文件")
    @RequiresPermissions("sys:file:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Result remove(int id) {
        if (fileService.remove(id) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

}
