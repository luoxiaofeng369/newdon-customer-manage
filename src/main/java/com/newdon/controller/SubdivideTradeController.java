package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.Insert;
import com.newdon.base.Result;
import com.newdon.base.Update;
import com.newdon.entity.SubdivideTrade;
import com.newdon.service.SubdivideTradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @ClassName SubdivideTradeService
 * @Auther: Dong
 * @Date: 2018/11/29 10:30
 * @Description: TODO
 **/

@RequestMapping("/newdon/subdivideTrade")
@CrossOrigin
@RestController
@Slf4j
public class SubdivideTradeController {
    @Autowired
    private SubdivideTradeService subdivideTradeService;

    @PostMapping(value = "/query")
    @ResponseBody
    public Result query(SubdivideTrade subdivideTrade, Integer page, Integer rows) {
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
        EntityWrapper<SubdivideTrade> wrapper = new EntityWrapper();
        wrapper.setEntity(subdivideTrade);
        Page<SubdivideTrade> pageInfo = this.subdivideTradeService.selectPage(new Page<>(page, rows), wrapper);
        return Result.build(200, "OK", pageInfo);
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    public Result insert(@Validated(Insert.class) @RequestBody SubdivideTrade subdivideTrade, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean insert = this.subdivideTradeService.insert(subdivideTrade);
        if (insert) {
            return Result.build(200, "OK", subdivideTrade.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Result update(@Validated(Update.class) @RequestBody SubdivideTrade subdivideTrade, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean b = this.subdivideTradeService.updateById(subdivideTrade);
        if (b) {
            return Result.build(200, "OK", subdivideTrade.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result delete(@RequestParam("id") Long id) {
        boolean b = this.subdivideTradeService.deleteById(id);
        if (b) {
            return Result.build(200, "OK", id);
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

}