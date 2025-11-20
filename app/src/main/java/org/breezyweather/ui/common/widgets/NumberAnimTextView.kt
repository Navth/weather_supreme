package org.breezyweather.ui.common.widgets

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.text.BidiFormatter
import android.util.AttributeSet
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * æ•°å­—å¢žåŠ åŠ¨ç”»çš„ã€€TextView
 *
 * @author bakumon
 * @date 16-11-26
 * @url https://github.com/Bakumon/NumberAnimTextView
 */
@SuppressLint("AppCompatCustomView")
class NumberAnimTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : TextView(context, attrs, defStyleAttr) {
    /**
     * èµ·å§‹å€¼ é»˜è®¤ 0
     */
    private var mNumStart = "0"

    /**
     * ç»“æŸå€¼
     */
    private var mNumEnd: String? = null

    /**
     * åŠ¨ç”»æ€»æ—¶é—´ é»˜è®¤ 2000 æ¯«ç§’
     */
    var duration: Long = 2000

    /**
     * å‰ç¼€
     */
    var prefixString = ""

    /**
     * åŽç¼€
     */
    var postfixString = ""

    /**
     * æ˜¯å¦å¼€å¯åŠ¨ç”»
     */
    var isAnimEnabled = true

    /**
     * æ˜¯å¦æ˜¯æ•´æ•°
     */
    private var isInt = false
    private var animator: ValueAnimator? = null

    @Suppress("unused")
    fun setNumberString(number: String) {
        setNumberString("0", number)
    }

    @SuppressLint("SetTextI18n")
    fun setNumberString(numberStart: String, numberEnd: String) {
        mNumStart = numberStart
        mNumEnd = numberEnd
        if (checkNumString(numberStart, numberEnd)) {
            // æ•°å­—åˆæ³•ã€€å¼€å§‹æ•°å­—åŠ¨ç”»
            start()
        } else {
            // æ•°å­—ä¸åˆæ³•ã€€ç›´æŽ¥è°ƒç”¨ã€€setTextã€€è®¾ç½®æœ€ç»ˆå€¼
            text = prefixString + BidiFormatter.getInstance().unicodeWrap(numberEnd) + postfixString
        }
    }

    /**
     * æ ¡éªŒæ•°å­—çš„åˆæ³•æ€§
     *
     * @param numberStart ã€€å¼€å§‹çš„æ•°å­—
     * @param numberEnd   ã€€ç»“æŸçš„æ•°å­—
     * @return åˆæ³•æ€§
     */
    private fun checkNumString(numberStart: String, numberEnd: String): Boolean {
        val regexInteger = "-?\\d*"
        isInt = numberEnd.matches(regexInteger.toRegex()) && numberStart.matches(regexInteger.toRegex())
        if (isInt) {
            return true
        }
        val regexDecimal = "-?[1-9]\\d*.\\d*|-?0.\\d*[1-9]\\d*"
        if ("0" == numberStart) {
            if (numberEnd.matches(regexDecimal.toRegex())) {
                return true
            }
        }
        return numberEnd.matches(regexDecimal.toRegex()) && numberStart.matches(regexDecimal.toRegex())
    }

    @SuppressLint("SetTextI18n")
    private fun start() {
        if (!isAnimEnabled) {
            // ç¦æ­¢åŠ¨ç”»
            text = prefixString + format(BigDecimal(mNumEnd)) + postfixString
            return
        }
        val f = BidiFormatter.getInstance()
        animator = ValueAnimator.ofObject(
            BigDecimalEvaluator(),
            BigDecimal(mNumStart),
            BigDecimal(mNumEnd)
        ).apply {
            duration = this@NumberAnimTextView.duration
            interpolator = DecelerateInterpolator(3f)
            addUpdateListener { valueAnimator: ValueAnimator ->
                val value = valueAnimator.animatedValue as BigDecimal
                text = prefixString + f.unicodeWrap(format(value)) + postfixString
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    text = prefixString + f.unicodeWrap(mNumEnd) + postfixString
                }
            })
        }.also { it.start() }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator?.cancel()
    }

    /**
     * æ ¼å¼åŒ– BigDecimal ,å°æ•°éƒ¨åˆ†æ—¶ä¿ç•™ä¸¤ä½å°æ•°å¹¶å››èˆäº”å…¥
     *
     * @param bd ã€€BigDecimal
     * @return æ ¼å¼åŒ–åŽçš„ String
     */
    private fun format(bd: BigDecimal): String {
        val pattern = StringBuilder()
        if (isInt) {
            pattern.append("#,###")
        } else {
            var length = 0
            val s1 = mNumStart.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val s2 = mNumEnd!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val s = if (s1.size > s2.size) s1 else s2
            if (s.size > 1) {
                // å°æ•°éƒ¨åˆ†
                length = s[1].length
            }
            pattern.append("#,##0")
            if (length > 0) {
                pattern.append(".")
                for (i in 0 until length) {
                    pattern.append("0")
                }
            }
        }
        val df = DecimalFormat(pattern.toString())
        return df.format(bd)
    }

    private class BigDecimalEvaluator : TypeEvaluator<Any> {
        override fun evaluate(fraction: Float, startValue: Any, endValue: Any): Any {
            val start = startValue as BigDecimal
            val end = endValue as BigDecimal
            val result = end.subtract(start)
            return result.multiply(BigDecimal(fraction.toDouble())).add(start)
        }
    }
}
