package io.github.louistsaitszho.erg2

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class HistoryFragment : Fragment() {
    private val vm: HistoryFragmentViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm.sessionsLiveData.observe(viewLifecycleOwner, android.arch.lifecycle.Observer {
            TODO("throw the data to adapter")
        })
        vm.refreshingLiveData.observe(viewLifecycleOwner, android.arch.lifecycle.Observer {
            if (it == true) {
                TODO("Ask pull to refresh to init itself if necessary")
            } else if (it == false) {
                TODO("Ask pull to refresh to dismiss itself if necessary")
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, con: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, con, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.resetAndRefresh()
        //TODO setup pagination, and call [HistoryFragmentViewModel.fetchNextPage]

        button_add_session.setOnClickListener {
            TODO("navigation is not ready yet")
        }
    }
}

//TODO take a parameter: HistoryView
class HistoryFragmentViewModel(private val repository: Repository) : ViewModel() {
    val sessionsLiveData = MutableLiveData<List<Session>>()
    val refreshingLiveData = MutableLiveData<Boolean>()

    fun resetAndRefresh() {
        sessionsLiveData.postValue(emptyList())
        sessionsLiveData.postValue(repository.getAllSessions())
    }

    fun fetchNextPage() {
        //TODO fetch next page, not everything
        sessionsLiveData.postValue(repository.getAllSessions())
    }
}

class SessionViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
        LayoutContainer {
    fun bind(session: Session) {

    }
}