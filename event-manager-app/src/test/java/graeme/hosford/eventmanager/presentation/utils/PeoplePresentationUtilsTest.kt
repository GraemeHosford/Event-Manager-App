package graeme.hosford.eventmanager.presentation.utils

import android.content.res.Resources
import graeme.hosford.eventmanager.R
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PeoplePresentationUtilsTest {

    @RelaxedMockK
    private lateinit var resources: Resources

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getAttendeesSummary_returnsDefaultString_whenNoAttendees() {
        every { resources.getString(R.string.create_event_no_attendees_chosen) } returns "No Attendees Chosen"

        val result = PeoplePresentationUtils.getAttendeeSummary(resources, emptyList())

        Assert.assertEquals("No Attendees Chosen", result)
    }

}