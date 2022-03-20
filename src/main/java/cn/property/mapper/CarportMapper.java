package cn.property.mapper;


import cn.property.model.Carport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface CarportMapper {


    Carport selectCarportById(String id);

    List<Carport> selectCarportList(Map<String, Object> map);

    void insertCarport(Carport carport);

    void deleteCarportById(String id);

    void updateCarportById(Carport carport);

    Carport selectCarportByCarportNum(String carportNum);
}
