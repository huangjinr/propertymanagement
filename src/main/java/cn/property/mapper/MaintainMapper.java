package cn.property.mapper;


import cn.property.model.Maintain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * (Maintain)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-19 14:25:20
 */

@Repository
@Mapper
public interface MaintainMapper {

    Maintain selectMaintainById(String id);

    List<Maintain> selectMaintainList(Map<String, Object> map);

    void insertMaintain(Maintain maintain);

    void deleteMaintainById(String id);

    void updateMaintainById(Maintain maintain);

}

