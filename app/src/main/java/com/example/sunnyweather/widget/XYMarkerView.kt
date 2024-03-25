package com.example.sunnyweather.widget

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import com.example.sunnyweather.R
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import java.text.DecimalFormat

class XYMarkerView(context: Context?, private val xAxisValueFormatter: IAxisValueFormatter) :
    MarkerView(context, R.layout.custom_marker_view) {
    private val tvContent: TextView
    private val format: DecimalFormat

    init {
        tvContent = findViewById(R.id.tvContent)
        format = DecimalFormat("###.000")
    }

    override fun refreshContent(e: Entry, highlight: Highlight) {
        // 创建一个新的 XAxis 实例，用于作为 getFormattedValue 方法的第二个参数
        val xAxis = XAxis()

        // 设置需要的属性，比如标签大小、颜色等
        xAxis.textSize = 12f
        xAxis.textColor = Color.BLACK
        tvContent.text = "x：" + xAxisValueFormatter.getFormattedValue(
            e.x,
            xAxis
        ) + "，y：" + format.format(e.y.toDouble())
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-(width / 2).toFloat(), -height.toFloat())
    }
}