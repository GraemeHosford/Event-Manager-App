package graeme.hosford.eventmanager.presentation.common.model

interface SingleUiModelView<Model> {
    fun setData(model: Model)
}