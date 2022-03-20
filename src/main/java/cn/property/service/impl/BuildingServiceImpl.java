package cn.property.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.property.mapper.BuildingMapper;
import cn.property.model.Building;
import cn.property.service.BuildingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * (Building)表服务实现类
 *
 * @author makejava
 * @since 2022-02-12 09:43:08
 */
@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public Building selectBuildingById(String id) {
        return buildingMapper.selectBuildingById(id);
    }

    @Override
    public PageInfo<Building> selectBuildingList(Building building, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        if (building != null) {
            if (!StrUtil.hasEmpty(building.getBuildingNumber())) {
                map.put("buildingNumber", building.getBuildingNumber());
            }
        }
        PageHelper.startPage(page,limit);
        List<Building> buildingList = buildingMapper.selectBuildingList(map);
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);
        return pageInfo;
    }

    @Override
    public void insertBuilding(Building building) {
        building.setId(IdUtil.simpleUUID());
        building.setIsDel(0);
        buildingMapper.insertBuilding(building);
    }

    @Override
    public void deleteBuildingById(String id) {
        buildingMapper.deleteBuildingById(id);
    }

    @Override
    public void updateBuildingById(Building building) {
        buildingMapper.updateBuildingById(building);
    }
}
