package graeme.hosford.eventmanager.presentation.common.model

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class UiModelListProcessor<Entity, UiModel>(
    private val converter: UiModelConverter<Entity, UiModel>
) {

    private var callback: ProcessingCompleteCallback<UiModel>? = null

    interface ProcessingCompleteCallback<UiModel> {
        fun onProcessingComplete(models: List<UiModel>)

        fun onProcessingFailure()
    }

    fun registerProcessingCallback(callback: ProcessingCompleteCallback<UiModel>) {
        this.callback = callback
    }

    fun process(entities: List<Entity>) {
        Observable.fromArray(entities)
            .map { t ->
                val modelList = ArrayList<UiModel>()
                t.forEach {
                    modelList.add(converter.toUiModel(it))
                }

                modelList
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    callback?.onProcessingComplete(it)
                },
                {
                    callback?.onProcessingFailure()
                }
            ).dispose()
    }

}