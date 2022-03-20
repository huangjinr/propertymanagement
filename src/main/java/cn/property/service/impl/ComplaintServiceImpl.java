package cn.property.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.property.mapper.ComplaintMapper;
import cn.property.mapper.UserMapper;
import cn.property.model.Complaint;
import cn.property.model.SysUser;
import cn.property.service.ComplaintService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Complaint)表服务实现类
 *
 * @author makejava
 * @since 2022-03-19 14:25:26
 */
@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintMapper complaintMapper;


    @Override
    public Complaint selectComplaintById(String id) {
        return complaintMapper.selectComplaintById(id);
    }

    @Override
    public PageInfo<Complaint> selectComplaintList(Complaint complaint, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        if (complaint != null) {
            if (!StrUtil.hasEmpty(complaint.getComplaintTitle())) {
                map.put("complaintTitle", complaint.getComplaintTitle());
            }
        }
        PageHelper.startPage(page,limit);
        List<Complaint> complaintList = complaintMapper.selectComplaintList(map);
        PageInfo<Complaint> pageInfo = new PageInfo<Complaint>(complaintList);
        return pageInfo;
    }

    @Override
    public void insertComplaint(Complaint complaint) {
        complaint.setId(IdUtil.simpleUUID());
        complaint.setIsDel(0);
        complaint.setComplaintTime(new Date());
        complaintMapper.insertComplaint(complaint);
    }

    @Override
    public void deleteComplaintById(String id) {
        complaintMapper.deleteComplaintById(id);
    }

    @Override
    public void updateComplaintById(Complaint complaint) {
        complaintMapper.updateComplaintById(complaint);
    }
   
}
