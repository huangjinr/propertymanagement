package cn.property.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.property.mapper.MaintainMapper;
import cn.property.model.Maintain;
import cn.property.service.MaintainService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Maintain)表服务实现类
 *
 * @author makejava
 * @since 2022-03-19 14:25:26
 */
@Service
public class MaintainServiceImpl implements MaintainService {

    @Autowired
    private MaintainMapper maintainMapper;


    @Override
    public Maintain selectMaintainById(String id) {
        return maintainMapper.selectMaintainById(id);
    }

    @Override
    public PageInfo<Maintain> selectMaintainList(Maintain maintain, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        /*if (maintain != null) {
            if (!StrUtil.hasEmpty(maintain.getMaintainTitle())) {
                map.put("MaintainTitle", maintain.getMaintainTitle());
            }
        }*/
        PageHelper.startPage(page,limit);
        List<Maintain> maintainList = maintainMapper.selectMaintainList(map);
        PageInfo<Maintain> pageInfo = new PageInfo<Maintain>(maintainList);
        return pageInfo;
    }

    @Override
    public void insertMaintain(Maintain maintain) {
        maintain.setId(IdUtil.simpleUUID());
        maintain.setIsDel(0);
        maintain.setApplyTime(new Date());
        maintain.setMaintainStatus(0);
        maintainMapper.insertMaintain(maintain);
    }

    @Override
    public void deleteMaintainById(String id) {
        maintainMapper.deleteMaintainById(id);
    }

    @Override
    public void updateMaintainById(Maintain maintain) {
        maintainMapper.updateMaintainById(maintain);
    }

    @Override
    public void maintainSuccessById(String id) {
        Maintain maintain = new Maintain();
        maintain.setId(id);
        maintain.setMaintainStatus(1);
        maintain.setMaintainTime(new Date());
        maintainMapper.updateMaintainById(maintain);
    }

}
