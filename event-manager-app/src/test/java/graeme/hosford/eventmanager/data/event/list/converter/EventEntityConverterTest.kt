package graeme.hosford.eventmanager.data.event.list.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.entity.event.Event
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EventEntityConverterTest {

    private lateinit var converter: EventEntityConverter

    @RelaxedMockK
    private lateinit var document: DocumentSnapshot

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        converter = EventEntityConverter()
    }

    @Test
    fun eventEntityConverter_convert_returnsCorrectEntity() {
        every { document.getString(Event.NAME_FIELD) } returns "TestName"
        every { document.getString(Event.DESCRIPTION_FIELD) } returns "Desc"
        every { document.getString(Event.LOCATION_FIELD) } returns "Cork"

        val event = converter.convert(document)

        assertEquals("TestName", event.name)
        assertEquals("Desc", event.description)
        assertEquals("Cork", event.location)
    }

}