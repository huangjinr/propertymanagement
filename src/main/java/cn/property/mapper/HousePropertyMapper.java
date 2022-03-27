package cn.property.mapper;


import cn.property.model.HouseProperty;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface HousePropertyMapper {


    HouseProperty selectHousePropertyById(String id);

    List<HouseProperty> selectHousePropertyList(Map<String, Object> map);

    void insertHouseProperty(HouseProperty houseProperty);

    void deleteHousePropertyById(String id);

    void updateHousePropertyById(HouseProperty houseProperty);

}
