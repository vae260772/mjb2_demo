package com.perfbrainstorming.happyguessing

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.perfbrainstorming.happyguessing.R

//recycleViewItem===R.layout.item
class FirstFragment() : Fragment() {
    val TAG = "FirstFragment"
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

    private var baseRecyclerView: RecyclerView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
        initRecycleView()
        // setRecycleViewData(false)

    }

    open fun bindView(view: View) {
        baseRecyclerView = view.findViewById(R.id.recyclerView)
        Log.d(TAG, "bindView baseRecyclerView=$baseRecyclerView")
    }

    open fun initRecycleView() {
        mList.add(TypeItemBean1(1, "funny", R.mipmap.ziran))//搞笑


        mList.add(
            TypeItemBean1(
                2,
                "classical",
                R.mipmap.jianzhu
            )
        )//经典


        mList.add(TypeItemBean1(3, "animal", R.mipmap.shenti))//动物
        mList.add(
            TypeItemBean1(
                4,
                "cold joke",
                R.mipmap.wupin
            )
        )//冷笑话
        mList.add(TypeItemBean1(5, "math", R.mipmap.renwu))//数学


        mList.add(
            TypeItemBean1(
                6,
                "humor",
                R.mipmap.tianpingzuo
            )
        )//幽默


        // baseRecyclerView!!.layoutManager =
        //     LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        baseRecyclerView!!.layoutManager = GridLayoutManager(requireContext(), 4)

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

                helper.itemView.setOnClickListener {

                    val intent = Intent(getContext(), QuestionActivity::class.java)
                    intent.putExtra("type",item.getId())

                    startActivity(intent)

                }
            }
        }
    }

    private val mList: MutableList<TypeItemBean1> = ArrayList()
}