package com.newdon.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.SystemLevelAndQuantity;
import com.newdon.entity.TechnologyInfo;
import com.newdon.mapper.TechnologyInfoMapper;
import com.newdon.service.SystemLevelAndQuantityService;
import com.newdon.service.TechnologyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: LeiGang
 * @create: 2019-01-05 17:05
 * @description:
 **/
@Service
public class TechnologyInfoServiceImpl extends ServiceImpl<TechnologyInfoMapper, TechnologyInfo> implements TechnologyInfoService {
    @Autowired
    private SystemLevelAndQuantityService systemLevelAndQuantityService;

    public Map<String, String> getSystemLevelAndQuantity(String contractId) {
        Map<String, String> map = new HashMap<>();
        //TODO 查询出该合同ID所有的系统级别以及系统数量，合并为字符串
        SystemLevelAndQuantity s = new SystemLevelAndQuantity();
        s.setContractId(Long.valueOf(contractId));
        List<SystemLevelAndQuantity> list = this.systemLevelAndQuantityService.selectList(new EntityWrapper<>(s));
        if (null != list && list.size() > 0) {
            StringBuilder str = new StringBuilder();
            //合并为字符串
            for (SystemLevelAndQuantity sl : list) {
                String systemLevel = sl.getSystemLevel();
                Integer systemQuantity = sl.getSystemQuantity();
                str.append(systemLevel).append("=").append(systemQuantity).append("\n");
            }
            map.put(contractId, str.toString());
        } else {
            map.put(contractId, "无");
        }
        return map;
    }

    public Map<String, String> getDeviceInformationAndQuantity(String contractId) {
        //TODO 查询出该合同ID所有的设备信息和数量，合并为字符串
        Map<String, String> map = new HashMap<>();
        map.put(contractId, "无");
        return map;
    }
}
