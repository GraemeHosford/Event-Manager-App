package graeme.hosford.eventmanager.presentation.common.view.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import graeme.hosford.eventmanager.R

/**
 * Not the best name but it suits more or less. Also just using regular findViewNyId in
 * this class as ButterKnife seems unnecessary here.
 */
class SummaryTextView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private lateinit var image: ImageView
    private lateinit var titleText: TextView
    private lateinit var descText: TextView

    private var titleString: String? = null
    private var descString: String? = null
    private var imageDrawable: Drawable? = null

    init {
        View.inflate(context, R.layout.summary_text_view_layout, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.SummaryTextView, 0, 0)

        titleString = attributes.getString(R.styleable.SummaryTextView_titleText)
        descString = attributes.getString(R.styleable.SummaryTextView_descText)
        imageDrawable = attributes.getDrawable(R.styleable.SummaryTextView_imageDrawable)

        attributes.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        image = findViewById(R.id.summaryTextViewImage)
        titleText = findViewById(R.id.summaryTextViewTitle)
        descText = findViewById(R.id.summaryTextViewDesc)

        if (titleString == null || descString == null) {
            throw IllegalStateException("Must have values for title and description text")
        }

        if (imageDrawable != null) {
            image.setImageDrawable(imageDrawable)
            image.visibility = View.VISIBLE
        }

        titleText.text = titleString
        descText.text = descString
    }
}