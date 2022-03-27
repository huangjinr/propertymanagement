package cn.property.service;


import cn.property.model.HouseProperty;
import com.github.pagehelper.PageInfo;

/**
 * (Department)表服务接口
 *
 * @author makejava
 * @since 2022-02-12 09:43:08
 */
public interface HousePropertyService {

    HouseProperty selectHousePropertyById(String id);

    PageInfo<HouseProperty> selectHousePropertyList(HouseProperty houseProperty,Integer page,Integer limit);

    void insertHouseProperty(HouseProperty houseProperty);

    void deleteHousePropertyById(String id);

    void updateHousePropertyById(HouseProperty houseProperty);

}
