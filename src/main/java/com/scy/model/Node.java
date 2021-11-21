package com.scy.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/***
 * 树结构节点
 */
@Data
public class Node {
    private Integer id;
    private Integer parentId;
    private String name;
    private Boolean open=false;//默认折叠
    List<Node> children= new ArrayList<>();;
    private Boolean checked=false;//是否选中
}
