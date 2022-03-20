package cn.property.utils;

import cn.property.model.SysPermission;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SysPermissionTree {

    private List<SysPermission> nodes;

    /**
     * 创建一个新的实例 Tree.
     *
     * @param nodes
     */
    public SysPermissionTree(List<SysPermission> nodes) {
        this.nodes = nodes;
    }

    /**
     * buildTree
     * 描述:  创建树
     *
     * @return
     */
    public List<Map<String, Object>> buildTree() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (SysPermission node : nodes) {
            //这里判断父节点，需要自己更改判断
            if ("-1".equals(node.getParentId())) {
                Map<String, Object> map = buildTreeChildsMap(node);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * buildChildMap
     * 描述:生成Map节点
     *
     * @param childNode
     * @return
     */
    private Map<String, Object> buildTreeChildsMap(SysPermission childNode) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", childNode.getId());
        map.put("name", childNode.getPermissionName());
        map.put("code", childNode.getPermissionCode());
        map.put("url", childNode.getPermissionUrl());
        map.put("sort", childNode.getSort());
        map.put("checked", false);
        map.put("open", true);
        map.put("parentId", childNode.getParentId());
        List<Map<String, Object>> childs = buildTreeChilds(childNode);
        //if (!childs.isEmpty()) {
        map.put("children", childs);
        //}
        return map;
    }

    /**
     * buildChilds
     * 描述:  创建树下的节点。
     *
     * @param node
     * @return
     */
    private List<Map<String, Object>> buildTreeChilds(SysPermission node) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<SysPermission> childNodes = getChilds(node);
        for (SysPermission childNode : childNodes) {
            Map<String, Object> map = buildTreeChildsMap(childNode);
            list.add(map);
        }
        return list;
    }

    /**
     * getChilds
     * 描述:  获取子节点
     *
     * @param parentNode
     * @return List<Resource>
     */
    public List<SysPermission> getChilds(SysPermission parentNode) {
        List<SysPermission> childNodes = new ArrayList<SysPermission>();
        for (SysPermission node : nodes) {
            if (StringUtils.equals(node.getParentId(), parentNode.getId())) {
                childNodes.add(node);
            }
        }
        return childNodes;
    }
}
