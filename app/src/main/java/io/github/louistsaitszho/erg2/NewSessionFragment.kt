package io.github.louistsaitszho.erg2

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.threeten.bp.Duration
import org.threeten.bp.ZonedDateTime

class NewSessionFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_session, container, true)
    }
}

/**
 * This ViewModel belongs to the whole fragment, including the fragments inside the ViewPager
 */
class NewSessionFragmentViewModel(val repository: Repository) : ViewModel() {

    /**
     * Actually saving everything
     */
    fun saveSession(session: Session) {

    }

    fun setDuration(duration: Duration) {

    }

    fun setStartTime(startTime: ZonedDateTime) {

    }

    fun setDistance(distanceInMeter: Int) {

    }
}

class NewSessionPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> NewSessionInfoFragment()
            1 -> NewSessionAttachmentFragment()
            2 -> NewSessionPreviewFragment()
            else -> throw IllegalArgumentException("$position is not a valid position")
        }
    }

    override fun getCount(): Int = 3
}

class NewSessionInfoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_session_info, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

class NewSessionAttachmentFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_session_attachment, container, true)
    }
}

class NewSessionPreviewFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_session_preview, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}