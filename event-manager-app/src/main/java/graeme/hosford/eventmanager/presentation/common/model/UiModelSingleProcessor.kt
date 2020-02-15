package graeme.hosford.eventmanager.presentation.common.model

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class UiModelSingleProcessor<Entity, UiModel>(
    private val converter: UiModelConverter<Entity, UiModel>
) {

    private val disposable = CompositeDisposable()

    private lateinit var processingCallback: ProcessingCompleteCallback<UiModel>

    interface ProcessingCompleteCallback<UiModel> {
        fun onProcessingComplete(model: UiModel)

        fun onProcessingFailed()
    }

    fun registerProcessingCallback(callback: ProcessingCompleteCallback<UiModel>) {
        processingCallback = callback
    }

    fun process(entity: Entity) {
        disposable.add(
            Single.just(entity)
                .map {
                    converter.toUiModel(it)
                }.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        onProcessingFinished(it)
                    },
                    {
                        processingCallback.onProcessingFailed()
                    }
                )
        )
    }

    private fun onProcessingFinished(model: UiModel) {
        processingCallback.onProcessingComplete(model)
        disposable.clear()
    }

}