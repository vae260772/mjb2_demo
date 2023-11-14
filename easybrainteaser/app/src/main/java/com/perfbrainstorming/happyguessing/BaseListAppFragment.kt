package com.perfbrainstorming.happyguessing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.perfbrainstorming.happyguessing.R


abstract class BaseListAppFragment<T> : Fragment() {
    var TAG = "BaseListAppFragment"
    public var baseRecyclerView: RecyclerView? = null
    public val mList: MutableList<T> = ArrayList()


    var baseQuickAdapter: BaseQuickAdapter<T, BaseViewHolder> =
        object : BaseQuickAdapter<T, BaseViewHolder>(
            recycleViewItem, mList
        ) {
            protected override fun convert(helper: BaseViewHolder, item: T) {
                convertItem(helper, item)
            }
        }

    abstract fun setRecycleViewData(showLoading: Boolean)
    abstract fun convertItem(helper: BaseViewHolder, item: T)
    abstract val recycleViewItem: Int
    private var contentView: View? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (contentView == null) {
            contentView = inflater.inflate(layout(), container, false)
            Log.d(TAG, "onCreateView view=$contentView")
        }
        return contentView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView(view)
        initRecycleView()
        setRecycleViewData(false)
    }

    fun layout(): Int {
        return R.layout.activity_base_list2
    }

    open fun bindView(view: View) {
        baseRecyclerView = view.findViewById(R.id.recyclerView)
        Log.d(TAG, "bindView baseRecyclerView=$baseRecyclerView")
    }


    fun setRecycleViewList(data: List<T>) {
        mList.clear()//重置，再添加新list
        mList.addAll(data)
        baseQuickAdapter.notifyDataSetChanged()
    }

    /**
     * 列表
     *
     * @param contentView
     */
    open fun bindRecycleView(contentView: RecyclerView) {
        this.baseRecyclerView = contentView
        initRecycleView()
    }

    open fun initRecycleView() {
        baseRecyclerView!!.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        baseRecyclerView!!.adapter = baseQuickAdapter
    }
}