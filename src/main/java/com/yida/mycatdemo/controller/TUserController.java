package com.yida.mycatdemo.controller;

import com.yida.mycatdemo.entity.TUser;
import com.yida.mycatdemo.service.TUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (TUser)表控制层
 *
 * @author makejava
 * @since 2020-03-14 13:21:02
 */
@Controller
@RequestMapping("mycat")
public class TUserController {
    /**
     * 服务对象
     */
    @Resource
    private TUserService tUserService;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("readone")
    public String selectOne(Integer id, Model model) {
        TUser user = this.tUserService.queryById(id);
        List<TUser> list = new ArrayList<>();
        list.add(user);
        model.addAttribute("list",list);
        return "query";
    }

    @GetMapping("readlimit")
    public String selectAllByLimit(Integer pageIndex, Integer pageNum, Model model) {
        List<TUser> list = tUserService.queryAllByLimit(pageIndex,pageNum);
        model.addAttribute("list", list);
        return "query";
    }

    @PostMapping("add")
    public String insert(TUser user) {
        tUserService.insert(user);
        return "index";
    }

    @PostMapping("update")
    public String update(TUser user) {
        tUserService.update(user);
        return "index";
    }

    @GetMapping("delete")
    public String delete(Integer id) {
        tUserService.deleteById(id);
        return "index";
    }
}