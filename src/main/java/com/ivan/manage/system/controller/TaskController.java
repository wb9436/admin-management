package com.ivan.manage.system.controller;

import com.ivan.manage.configuration.annotation.Log;
import com.ivan.manage.configuration.controller.BaseController;
import com.ivan.manage.configuration.util.PageUtils;
import com.ivan.manage.configuration.util.Result;
import com.ivan.manage.system.dto.TaskDto;
import com.ivan.manage.system.service.ITaskService;
import com.ivan.manage.utils.Query;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/sys/task")
@Controller
public class TaskController extends BaseController {
	public String prefix = "system/task";

	@Autowired
	private ITaskService taskService;

	@RequiresPermissions("sys:task:task")
	@GetMapping()
	protected String taskScheduleJob() {
		return prefix + "/task";
	}

	@RequiresPermissions("sys:task:task")
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<TaskDto> taskScheduleJobList = taskService.list(query);
		int total = taskService.count(query);
		PageUtils pageUtils = new PageUtils(taskScheduleJobList, total);
		return pageUtils;
	}

	@Log("添加任务")
	@RequiresPermissions("sys:task:add")
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	@Log("编辑任务")
	@RequiresPermissions("sys:task:edit")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		TaskDto job = taskService.get(id);
		model.addAttribute("job", job);
		return prefix + "/edit";
	}

	@RequestMapping("/info/{id}")
	public Result info(@PathVariable("id") Integer id) {
		TaskDto taskScheduleJob = taskService.get(id);
		return Result.ok().put("taskScheduleJob", taskScheduleJob);
	}

	@Log("保存任务")
	@RequiresPermissions("sys:task:add")
	@ResponseBody
	@PostMapping("/save")
	public Result save(TaskDto taskScheduleJob) {
		if (taskService.save(taskScheduleJob) > 0) {
			return Result.ok();
		}
		return Result.error();
	}

	@Log("更新任务")
	@RequiresPermissions("sys:task:edit")
	@ResponseBody
	@PostMapping("/update")
	public Result update(TaskDto taskScheduleJob) {
		taskService.update(taskScheduleJob);
		return Result.ok();
	}

	@Log("删除任务")
	@RequiresPermissions("sys:task:remove")
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(Integer id) {
		if (taskService.remove(id) > 0) {
			return Result.ok();
		}
		return Result.error();
	}

	@RequiresPermissions("sys:task:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public Result remove(@RequestParam("ids[]") Integer[] ids) {
		taskService.batchRemove(ids);
		return Result.ok();
	}

	@RequiresPermissions("sys:task:change")
	@PostMapping(value = "/changeJobStatus")
	@ResponseBody
	public Result changeJobStatus(Integer id, String cmd) {
		String label = "停止";
		if ("start".equals(cmd)) {
			label = "启动";
		} else {
			label = "停止";
		}
		try {
			taskService.changeStatus(id, cmd);
			return Result.ok("任务" + label + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.ok("任务" + label + "失败");
	}

}
