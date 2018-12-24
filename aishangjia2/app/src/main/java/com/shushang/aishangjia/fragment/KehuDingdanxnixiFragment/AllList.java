package com.shushang.aishangjia.fragment.KehuDingdanxnixiFragment;

import android.view.View;
import android.widget.TextView;

import com.shushang.aishangjia.R;

import me.texy.treeview.TreeNode;
import me.texy.treeview.base.BaseNodeViewBinder;

public class AllList extends BaseNodeViewBinder {

    private TextView all_name;
    public AllList(View itemView) {
        super(itemView);
        all_name = (TextView) itemView.findViewById(R.id.text);
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_kehudingdantitle;
    }

    @Override
    public void bindView(TreeNode treeNode) {

    }
    /** 根据是否展开来变换图标 */
    @Override
    public void onNodeToggled(TreeNode treeNode, boolean expand) {

    }
}

