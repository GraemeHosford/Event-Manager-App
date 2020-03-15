package graeme.hosford.eventmanager.presentation.common.model

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class UiModelListProcessor<Entity, UiModel>(
    private val converter: UiModelConverter<Entity, UiModel>
) {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private var callback: ProcessingCompleteCallback<UiModel>? = null

    private var listComparator: Comparator<UiModel>? = null

    interface ProcessingCompleteCallback<UiModel> {
        fun onProcessingComplete(models: List<UiModel>)

        fun onProcessingFailure()
    }

    fun registerProcessingCallback(callback: ProcessingCompleteCallback<UiModel>) {
        this.callback = callback
    }

    fun setListComparator(comparator: Comparator<UiModel>) {
        this.listComparator = comparator
    }

    fun process(entities: List<Entity>) {
        /* Having listComparator as a lateinit var does not give an informative error message if the
        * comparator is not set. This way an explicit exception makes the problem more obvious */
        if (listComparator == null) {
            throw IllegalStateException("A list comparator must be set for any use of UiModelListProcessor")
        }

        disposable.add(Observable.fromArray(entities)
            .map { t ->
                val modelList = ArrayList<UiModel>()
                t.forEach {
                    modelList.add(converter.toUiModel(it))
                }

                modelList.sortedWith(listComparator!!)
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onProcessingFinished(it)
                },
                {
                    callback?.onProcessingFailure()
                }
            )
        )
    }

    private fun onProcessingFinished(entities: List<UiModel>) {
        callback?.onProcessingComplete(entities)
        disposable.clear()
    }

}