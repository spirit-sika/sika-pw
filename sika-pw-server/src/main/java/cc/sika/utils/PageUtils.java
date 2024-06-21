package cc.sika.utils;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PageUtils {
    /**
     * 将PO分页结果转为VO分页结果, 此方法只处理泛型, 后续数据需要手动填充
     * @param poPageInfo po分页查询结果
     * @return vo分页信息
     * @param <P> PO
     * @param <V> VO
     */
    @SuppressWarnings("unchecked")
    public static <P, V> PageInfo<V> toVO(PageInfo<P> poPageInfo) {
        return BeanUtil.copyProperties(poPageInfo, PageInfo.class, "list");
    }

    /**
     * 根据Function接口提供的转换规则将PO分页查询结果转为VO分页信息
     * @param poPageInfo po分页查询结果
     * @param convertor 转换规则
     * @return vo分页信息
     * @param <P> PO
     * @param <V> VO
     */
    @SuppressWarnings("unchecked")
    public static <P, V> PageInfo<V> toVO(PageInfo<P> poPageInfo, Function<P, V> convertor) {
        PageInfo<V> voPageInfo = BeanUtil.copyProperties(poPageInfo, PageInfo.class, "list");
        List<V> voList = new ArrayList<>();
        // po 转 vo
        poPageInfo.getList().forEach(item -> voList.add(convertor.apply(item)));
        voPageInfo.setList(voList);
        return voPageInfo;
    }
}
