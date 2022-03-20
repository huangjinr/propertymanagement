package cn.property.mapper;


import cn.property.model.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * (Notice)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-19 14:25:20
 */

@Repository
@Mapper
public interface NoticeMapper {

    Notice selectNoticeById(String id);

    List<Notice> selectNoticeList(Map<String, Object> map);

    void insertNotice(Notice notice);

    void deleteNoticeById(String id);

    void updateNoticeById(Notice notice);

}

