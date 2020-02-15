package graeme.hosford.eventmanager.presentation.common.view.recyclerview

interface RecyclerViewListView<UiModel> {

    fun showData(items: List<UiModel>)

    fun showErrorScreen()

}