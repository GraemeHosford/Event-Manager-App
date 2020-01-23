package graeme.hosford.eventmanager.presentation.common.model

interface UiModelConverter<Entity, UiModel> {

    fun toUiModel(entity: Entity): UiModel

}