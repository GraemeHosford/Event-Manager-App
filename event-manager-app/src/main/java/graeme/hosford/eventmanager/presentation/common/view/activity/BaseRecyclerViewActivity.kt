package graeme.hosford.eventmanager.presentation.common.view.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.floatingactionbutton.FloatingActionButton
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseAdapter
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseViewHolder
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.RecyclerViewListView

abstract class BaseRecyclerViewActivity<
        Model,
        ViewHolder : BaseViewHolder<Model>,
        Adapter : BaseAdapter<Model, ViewHolder>
        > : BaseActivity(), RecyclerViewListView<Model> {

    @BindView(R.id.recycler_view)
    lateinit var recyclerview: RecyclerView

    @BindView(R.id.loading_bar)
    lateinit var loadingBar: ProgressBar

    @BindView(R.id.error_text_view)
    lateinit var errorMessage: TextView

    @BindView(R.id.no_items_text_view)
    lateinit var emptyItems: TextView

    @BindView(R.id.fab)
    lateinit var fab: FloatingActionButton

    private lateinit var recyclerViewAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getRecyclerViewLayout())
        ButterKnife.bind(this)

        this.recyclerViewAdapter = recyclerViewAdapter()
        val layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = recyclerViewAdapter

        addRecyclerViewDecorations(recyclerview.context, layoutManager.orientation).forEach {
            recyclerview.addItemDecoration(it)
        }

        if (showFab()) {
            fab.show()
        } else {
            fab.hide()
        }
    }

    @LayoutRes
    protected open fun getRecyclerViewLayout(): Int = R.layout.base_recycler_view_layout

    protected open fun showFab(): Boolean {
        return false
    }

    protected open fun addRecyclerViewDecorations(
        recyclerViewContext: Context,
        layoutOrientation: Int
    ): List<RecyclerView.ItemDecoration> =
        listOf(DividerItemDecoration(recyclerViewContext, layoutOrientation))

    protected abstract fun recyclerViewAdapter(): Adapter

    override fun showData(items: List<Model>) {
        if (items.isNotEmpty()) {
            recyclerViewAdapter.setItems(items)
            recyclerview.visibility = View.VISIBLE
        } else {
            emptyItems.visibility = View.VISIBLE
        }

        /* Hide loading bar after items have been set to avoid flicker which can
        sometimes happen when hiding before RecyclerView is updated */
        loadingBar.visibility = View.GONE
    }

    override fun showErrorScreen() {
        loadingBar.visibility = View.INVISIBLE
        errorMessage.visibility = View.VISIBLE
    }

}