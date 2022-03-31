package org.iproute.complexGraph.define;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhuzhenjie
 * 自定义图节点hashcode的类
 */
public class HashCodeBuilder {

    /**
     * 节点hash自增
     */
    private static volatile Integer increment = Integer.MIN_VALUE;

    /**
     * 边 hash自增
     */
    private static volatile Integer edgeIncrement = Integer.MIN_VALUE;

    /**
     * 存取唯一标志和code的地方 用于点
     */
    private static final ConcurrentHashMap<String, Integer> SIGN_TO_CODE =
            new ConcurrentHashMap<>();

    /**
     * 边值的hashCode
     */
    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> EDGE_CODE = new
            ConcurrentHashMap<>();

    /**
     * 获取hashCode的地方 利用map的key的唯一性 string 和 int 组成了equals() 和 hashCode() 的重载
     *
     * @param sign 唯一标识
     * @return hashCode
     */
    static synchronized Integer getCode(String sign) {
        Integer code = SIGN_TO_CODE.get(sign);
        if (null == code) {
            code = ++increment;
            SIGN_TO_CODE.put(sign, code);
        }
        return code;
    }

    /**
     * 边利用两点获取唯一的hashCode
     *
     * @param sign1 标志1
     * @param sign2 标志2
     * @return hash值
     */
    static synchronized Integer getCode(String sign1, String sign2) {
        ConcurrentHashMap<String, Integer> sign2ToCode = EDGE_CODE.get(sign1);
        if (null == sign2ToCode) {
            sign2ToCode = new ConcurrentHashMap<>();
            Integer code = ++edgeIncrement;
            sign2ToCode.put(sign2, code);
            EDGE_CODE.put(sign1, sign2ToCode);
            return code;
        } else {
            Integer code = sign2ToCode.get(sign2);
            if (null == code) {
                code = ++edgeIncrement;
                sign2ToCode.put(sign2, code);
            }
            return code;
        }
    }

}
