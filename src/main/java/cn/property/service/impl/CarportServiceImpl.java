package cn.property.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.property.mapper.BuildingMapper;
import cn.property.mapper.CarportMapper;
import cn.property.mapper.UserMapper;
import cn.property.model.Carport;
import cn.property.service.BuildingService;
import cn.property.service.CarportService;
import cn.property.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * (Carport)表服务实现类
 *
 * @author makejava
 * @since 2022-02-12 09:43:08
 */
@Service
public class CarportServiceImpl implements CarportService {

    @Autowired
    private CarportMapper carportMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public Carport selectCarportById(String id) {
        return carportMapper.selectCarportById(id);
    }

    @Override
    public PageInfo<Carport> selectCarportList(Carport carport, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        if (carport != null) {
            if (!StrUtil.hasEmpty(carport.getCarportNum())) {
                map.put("carportNum", carport.getCarportNum());
            }
        }
        PageHelper.startPage(page,limit);
        List<Carport> carportList = carportMapper.selectCarportList(map);
        PageInfo<Carport> pageInfo = new PageInfo<Carport>(carportList);
        return pageInfo;
    }

    @Override
    public void insertCarport(Carport carport) {
        carport.setUserId(userMapper.selectUserByName(carport.getUserName()).getId());
        carport.setBuildingId(buildingMapper.selectBuildingByNumber(carport.getBuildingNumber()).getId());
        carport.setId(IdUtil.simpleUUID());
        carport.setIsDel(0);
        carportMapper.insertCarport(carport);
    }

    @Override
    public void deleteCarportById(String id) {
        carportMapper.deleteCarportById(id);
    }

    @Override
    public void updateCarportById(Carport carport) {
        carportMapper.updateCarportById(carport);
    }

    @Override
    public Carport selectCarportByCarportNum(String carportNum) {
        return carportMapper.selectCarportByCarportNum(carportNum);
    }
}
