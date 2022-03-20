package cn.property.mapper;


import cn.property.model.Building;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BuildingMapper {


    Building selectBuildingById(String id);

    List<Building> selectBuildingList(Map<String, Object> map);

    void insertBuilding(Building building);

    void deleteBuildingById(String id);

    void updateBuildingById(Building building);
}
