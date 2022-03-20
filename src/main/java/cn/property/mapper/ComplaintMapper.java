package cn.property.mapper;


import cn.property.model.Complaint;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * (Complaint)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-19 14:25:20
 */

@Repository
@Mapper
public interface ComplaintMapper {

    Complaint selectComplaintById(String id);

    List<Complaint> selectComplaintList(Map<String, Object> map);

    void insertComplaint(Complaint complaint);

    void deleteComplaintById(String id);

    void updateComplaintById(Complaint complaint);

}

