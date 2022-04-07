package cn.property.controller;

import cn.property.model.Complaint;
import cn.property.model.SysUser;
import cn.property.service.ComplaintService;
import cn.property.utils.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Complaint)表控制层
 *
 * @author makejava
 * @since 2022-03-19 14:25:20
 */
@Controller
@RequestMapping("complaint")
public class ComplaintController {
    /**
     * 服务对象
     */
    @Resource
    private ComplaintService complaintService;


    @RequestMapping("index")
    private String index(){
        return "views/complaint/list.html";
    }


    @RequestMapping(value = "selectComplaintList", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap selectComplaintList(Authentication authentication,Complaint complaint, Integer page, Integer limit) {
        SysUser principal = (SysUser) authentication.getPrincipal();
        if (principal.getSysRole().getRoleType() == 2){
            complaint.setUserId(principal.getId());
        }
        PageInfo<Complaint> complaintList = complaintService.selectComplaintList(complaint,page,limit);
        return ReturnUtil.Success("查询成功", complaintList.getList(), complaintList.getTotal());
    }

    @PostMapping("insertComplaint")
    @ResponseBody
    public ModelMap insertComplaint(Complaint complaint, Authentication authentication) {
        SysUser user = (SysUser) authentication.getPrincipal();
        complaint.setUserId(user.getId());
        try {
            complaintService.insertComplaint(complaint);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @GetMapping("deleteComplaintById")
    @ResponseBody
    public ModelMap deleteComplaintById(String id) {
        try {
            complaintService.deleteComplaintById(id);
            return ReturnUtil.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败");
        }
    }

    @PostMapping("updateComplaintById")
    @ResponseBody
    public ModelMap updateComplaintById(Complaint complaint) {

        try {
            complaintService.updateComplaintById(complaint);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @RequestMapping("updateComplaintIndex")
    public String selectComplaintById(String id, Model model) {
        Complaint complaint = complaintService.selectComplaintById(id);
        model.addAttribute("complaint", complaint);
        return "views/complaint/complaintform";
    }
}

