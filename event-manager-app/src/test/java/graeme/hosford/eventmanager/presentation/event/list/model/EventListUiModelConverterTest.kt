package graeme.hosford.eventmanager.presentation.event.list.model

import graeme.hosford.eventmanager.entity.event.Event
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListUiModelConverter
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
        converter =
            EventListUiModelConverter()
    }

    @Test
    fun toUiModel_returnsCOrrectUiModelFromEntity() {
        val entity = Event(
            "123", "Graeme", "Event Name",
            "Description", startDate, endDate, "Cork", 0.0, 0.0
        )
        val model = converter.toUiModel(entity)

        assertEquals(entity.id, model.id)
        assertEquals(entity.owner, model.eventOwner)
        assertEquals(entity.name, model.eventName)
        assertEquals(entity.description, model.eventDesc)
        assertEquals(entity.startDate, model.startDate)
        assertEquals(entity.endDate, model.endDate)
        assertEquals(entity.locationName, model.eventLocation)
    }

}