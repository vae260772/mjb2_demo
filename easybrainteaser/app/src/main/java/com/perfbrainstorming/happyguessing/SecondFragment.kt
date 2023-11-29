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

class SecondFragment : Fragment() {
    val TAG = "SecondFragment"

    private var baseRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contentView = inflater.inflate(R.layout.activity_base_list2, container, false);
        return contentView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
        initRecycleView()
        // setRecycleViewData(false)
        baseRecyclerView!!.adapter = object : BaseQuickAdapter<TypeItemBean1, BaseViewHolder>(
            R.layout.rv_item1, mList
        ) {
            protected override fun convert(helper: BaseViewHolder, item: TypeItemBean1) {
                convertItem(helper, item)
            }

            private fun convertItem(helper: BaseViewHolder, item: TypeItemBean1) {
                //bean
                helper.setImageResource(R.id.ivgxiao, item.getDraw())
                helper.setText(R.id.tvgxiao, item.getName())
            }
        }
    }

    open fun bindView(view: View) {
        baseRecyclerView = view.findViewById(R.id.recyclerView)
        //Log.d(TAG, "bindView baseRecyclerView=$baseRecyclerView")
    }

    open fun initRecycleView() {
        // baseRecyclerView!!.layoutManager =
        //     LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        baseRecyclerView!!.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // GridLayoutManager()


        baseRecyclerView!!.adapter = object : BaseQuickAdapter<TypeItemBean1, BaseViewHolder>(
            R.layout.rv_item1, mList
        ) {
            protected override fun convert(helper: BaseViewHolder, item: TypeItemBean1) {
                convertItem(helper, item)
            }

            private fun convertItem(helper: BaseViewHolder, item: TypeItemBean1) {
                //bean
                helper.setImageResource(R.id.ivgxiao, item.getDraw())
                helper.setText(R.id.tvgxiao, item.getName())
            }
        }
    }

    private val mList: MutableList<TypeItemBean1> = ArrayList()

}