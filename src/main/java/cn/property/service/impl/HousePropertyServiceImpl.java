package cn.property.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.property.mapper.HousePropertyMapper;
import cn.property.mapper.UserMapper;
import cn.property.model.HouseProperty;
import cn.property.service.HousePropertyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * (HouseProperty)表服务实现类
 *
 * @author makejava
 * @since 2022-02-12 09:43:08
 */
@Service
public class HousePropertyServiceImpl implements HousePropertyService {

    @Autowired
    private HousePropertyMapper housePropertyMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public HouseProperty selectHousePropertyById(String id) {
        return housePropertyMapper.selectHousePropertyById(id);
    }

    @Override
    public PageInfo<HouseProperty> selectHousePropertyList(HouseProperty houseProperty, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        if (houseProperty != null) {
            if (!StrUtil.hasEmpty(houseProperty.getUserName())) {
                map.put("userName", houseProperty.getUserName());
            }
            if (!StrUtil.hasEmpty(houseProperty.getUserId())) {
                map.put("userId", houseProperty.getUserId());
            }
        }
        PageHelper.startPage(page,limit);
        List<HouseProperty> housePropertyList = housePropertyMapper.selectHousePropertyList(map);
        PageInfo<HouseProperty> pageInfo = new PageInfo<HouseProperty>(housePropertyList);
        return pageInfo;
    }

    @Override
    public void insertHouseProperty(HouseProperty houseProperty) {
        houseProperty.setUserId(userMapper.selectUserByName(houseProperty.getUserName()).getId());
        houseProperty.setId(IdUtil.simpleUUID());
        houseProperty.setIsDel(0);
        housePropertyMapper.insertHouseProperty(houseProperty);
    }

    @Override
    public void deleteHousePropertyById(String id) {
        housePropertyMapper.deleteHousePropertyById(id);
    }

    @Override
    public void updateHousePropertyById(HouseProperty houseProperty) {
        housePropertyMapper.updateHousePropertyById(houseProperty);
    }

}
