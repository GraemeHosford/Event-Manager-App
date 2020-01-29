package graeme.hosford.eventmanager.data.event.list.converter

import com.google.firebase.firestore.DocumentSnapshot
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
        every { document.getString("name") } returns "TestName"

        val event = converter.convert(document)

        assertEquals("TestName", event.name)
    }

}