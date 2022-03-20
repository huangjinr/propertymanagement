package cn.property.controller;

import cn.property.model.Notice;
import cn.property.model.SysUser;
import cn.property.service.NoticeService;
import cn.property.utils.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Notice)表控制层
 *
 * @author makejava
 * @since 2022-03-19 14:25:20
 */
@Controller
@RequestMapping("notice")
public class NoticeController {
    /**
     * 服务对象
     */
    @Resource
    private NoticeService noticeService;


    @RequestMapping("index")
    private String index(){
        return "views/notice/list.html";
    }


    @RequestMapping(value = "selectNoticeList", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap selectNoticeList(Notice notice, Integer page, Integer limit) {
        PageInfo<Notice> noticeList = noticeService.selectNoticeList(notice,page,limit);
        return ReturnUtil.Success("查询成功", noticeList.getList(), noticeList.getTotal());
    }

    @PostMapping("insertNotice")
    @ResponseBody
    public ModelMap insertNotice(Notice notice, Authentication authentication) {
        try {
            noticeService.insertNotice(notice);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @GetMapping("deleteNoticeById")
    @ResponseBody
    public ModelMap deleteNoticeById(String id) {
        try {
            noticeService.deleteNoticeById(id);
            return ReturnUtil.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败");
        }
    }

    @PostMapping("updateNoticeById")
    @ResponseBody
    public ModelMap updateNoticeById(Notice notice) {

        try {
            noticeService.updateNoticeById(notice);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @RequestMapping("updateNoticeIndex")
    public String selectNoticeById(String id, Model model) {
        Notice notice = noticeService.selectNoticeById(id);
        model.addAttribute("notice", notice);
        return "views/notice/noticeform";
    }
}

