package graeme.hosford.eventmanager.presentation.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseAdapter
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseViewHolder

abstract class BaseRecyclerViewFragment<
        Model,
        ViewHolder : BaseViewHolder<Model>,
        Adapter : BaseAdapter<Model, ViewHolder>> :
    BaseFragment() {

    @BindView(R.id.recycler_view)
    lateinit var recyclerview: RecyclerView

    @BindView(R.id.loading_bar)
    lateinit var loadingBar: ProgressBar

    @BindView(R.id.error_text_view)
    lateinit var errorMessage: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.base_recycler_view_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview.adapter = recyclerViewAdapter()
        val layoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager = layoutManager
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                recyclerview.context,
                layoutManager.orientation
            )
        )
    }

    protected abstract fun recyclerViewAdapter(): Adapter
}