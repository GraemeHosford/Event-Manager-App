package graeme.hosford.eventmanager.presentation.attendees.view

import android.os.Bundle
import android.widget.Button
import butterknife.BindView
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.attendees.AttendeesPresenter
import graeme.hosford.eventmanager.presentation.attendees.AttendeesView
import graeme.hosford.eventmanager.presentation.attendees.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.attendees.view.adapter.AttendeesAdapter
import graeme.hosford.eventmanager.presentation.attendees.view.adapter.AttendeesViewHolder
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import javax.inject.Inject

class AttendeesFragment :
    BaseRecyclerViewFragment<AttendeesUiModel, AttendeesViewHolder, AttendeesAdapter>(),
    AttendeesView {

    @Inject
    lateinit var presenter: AttendeesPresenter

    @BindView(R.id.confirm_attendees_button)
    lateinit var confirmAttendeesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun getRecyclerViewLayout(): Int = R.layout.choose_attendees_layout

    override fun recyclerViewAdapter(): AttendeesAdapter = AttendeesAdapter()
}