package graeme.hosford.eventmanager.presentation.event.list.model

import graeme.hosford.eventmanager.entity.event.Event
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class EventListUiModelConverterTest {

    private lateinit var converter: EventListUiModelConverter

    @RelaxedMockK
    private lateinit var startDate: Calendar

    @RelaxedMockK
    private lateinit var endDate: Calendar

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        converter = EventListUiModelConverter()
    }

    @Test
    fun toUiModel_returnsCOrrectUiModelFromEntity() {
        val entity = Event("123", "Event Name", "Description", startDate, endDate, "Cork")
        val model = converter.toUiModel(entity)

        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.eventName)
        assertEquals(entity.description, model.eventDesc)
        assertEquals(entity.location, model.eventLocation)
    }

}