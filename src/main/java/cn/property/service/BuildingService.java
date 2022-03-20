package cn.property.service;


import cn.property.model.Building;
import com.github.pagehelper.PageInfo;

/**
 * (Department)表服务接口
 *
 * @author makejava
 * @since 2022-02-12 09:43:08
 */
public interface BuildingService {

    Building selectBuildingById(String id);

    PageInfo<Building> selectBuildingList(Building building,Integer page,Integer limit);

    void insertBuilding(Building building);

    void deleteBuildingById(String id);

    void updateBuildingById(Building building);

}
