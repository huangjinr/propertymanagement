package cn.property.service;


import cn.property.model.Carport;
import com.github.pagehelper.PageInfo;

/**
 * (Department)表服务接口
 *
 * @author makejava
 * @since 2022-02-12 09:43:08
 */
public interface CarportService {

    Carport selectCarportById(String id);

    PageInfo<Carport> selectCarportList(Carport carport,Integer page,Integer limit);

    void insertCarport(Carport carport);

    void deleteCarportById(String id);

    void updateCarportById(Carport carport);

    Carport selectCarportByCarportNum(String carportNum);
}
