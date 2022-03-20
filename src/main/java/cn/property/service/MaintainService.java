package cn.property.service;


import cn.property.model.Maintain;
import com.github.pagehelper.PageInfo;

/**
 * (Maintain)表服务接口
 *
 * @author makejava
 * @since 2022-03-19 14:25:26
 */
public interface MaintainService {

    Maintain selectMaintainById(String id);

    PageInfo<Maintain> selectMaintainList(Maintain maintain, Integer page, Integer limit);

    void insertMaintain(Maintain maintain);

    void deleteMaintainById(String id);

    void updateMaintainById(Maintain maintain);

    void maintainSuccessById(String id);
}
