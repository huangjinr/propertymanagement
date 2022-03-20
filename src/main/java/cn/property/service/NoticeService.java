package cn.property.service;


import cn.property.model.Notice;
import com.github.pagehelper.PageInfo;

/**
 * (Notice)表服务接口
 *
 * @author makejava
 * @since 2022-03-19 14:25:26
 */
public interface NoticeService {

    Notice selectNoticeById(String id);

    PageInfo<Notice> selectNoticeList(Notice notice, Integer page, Integer limit);

    void insertNotice(Notice notice);

    void deleteNoticeById(String id);

    void updateNoticeById(Notice notice);

}
