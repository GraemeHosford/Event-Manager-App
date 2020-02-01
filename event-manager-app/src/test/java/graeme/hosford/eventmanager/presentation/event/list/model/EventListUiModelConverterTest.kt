package graeme.hosford.eventmanager.presentation.event.list.model

import graeme.hosford.eventmanager.entity.event.Event
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EventListUiModelConverterTest {

    private lateinit var converter: EventListUiModelConverter

    @Before
    fun setup() {
        converter = EventListUiModelConverter()
    }

    @Test
    fun toUiModel_returnsCOrrectUiModelFromEntity() {
        val entity = Event("Event Name", "Description", "Cork")
        val model = converter.toUiModel(entity)

        assertEquals(entity.name, model.eventName)
        assertEquals(entity.description, model.eventDesc)
        assertEquals(entity.location, model.eventLocation)
    }

}