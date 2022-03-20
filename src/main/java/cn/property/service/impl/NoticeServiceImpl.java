package cn.property.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.property.mapper.NoticeMapper;
import cn.property.mapper.UserMapper;
import cn.property.model.Notice;
import cn.property.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Notice)表服务实现类
 *
 * @author makejava
 * @since 2022-03-19 14:25:26
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public Notice selectNoticeById(String id) {
        return noticeMapper.selectNoticeById(id);
    }

    @Override
    public PageInfo<Notice> selectNoticeList(Notice notice, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        if (notice != null) {
            if (!StrUtil.hasEmpty(notice.getNoticeTitle())) {
                map.put("noticeTitle", notice.getNoticeTitle());
            }
        }
        PageHelper.startPage(page,limit);
        List<Notice> noticeList = noticeMapper.selectNoticeList(map);
        PageInfo<Notice> pageInfo = new PageInfo<Notice>(noticeList);
        return pageInfo;
    }

    @Override
    public void insertNotice(Notice notice) {
        notice.setId(IdUtil.simpleUUID());
        notice.setIsDel(0);
        notice.setNoticeTime(new Date());
        noticeMapper.insertNotice(notice);
    }

    @Override
    public void deleteNoticeById(String id) {
        noticeMapper.deleteNoticeById(id);
    }

    @Override
    public void updateNoticeById(Notice notice) {
        noticeMapper.updateNoticeById(notice);
    }
   
}
