package cn.property.service;


import cn.property.model.Complaint;
import com.github.pagehelper.PageInfo;

/**
 * (Complaint)表服务接口
 *
 * @author makejava
 * @since 2022-03-19 14:25:26
 */
public interface ComplaintService {

    Complaint selectComplaintById(String id);

    PageInfo<Complaint> selectComplaintList(Complaint complaint, Integer page, Integer limit);

    void insertComplaint(Complaint complaint);

    void deleteComplaintById(String id);

    void updateComplaintById(Complaint complaint);

}
