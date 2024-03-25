package com.example.sunnyweather.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunnyweather.R
import com.example.sunnyweather.widget.DecimalFormatter
import com.example.sunnyweather.widget.MyAxisValueFormatter
import com.example.sunnyweather.widget.XAxisValueFormatter
import com.example.sunnyweather.widget.XYMarkerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import io.reactivex.annotations.Nullable


class FifteenActivity : AppCompatActivity(), OnChartValueSelectedListener {
    protected var mChart: BarChart? = null
    private var hBarChart: HorizontalBarChart? = null
    private var lineChart: LineChart? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifteen)
        mChart = findViewById(R.id.chart1)
        hBarChart = findViewById(R.id.hBarChart)
        lineChart = findViewById(R.id.lineChart)
        initBarChart()
        initHBarChart()
        initLineChart()
    }

    /**
     * 初始化柱形图控件属性
     */
    private fun initBarChart() {
        /*mChart!!.setOnChartValueSelectedListener(this)：设置当图表中的数值被选中时的监听器为当前类（this），
        即设置了数值选中的回调监听。

mChart!!.setDrawBarShadow(false)：设置是否绘制柱形图的阴影效果，这里将阴影效果关闭。

mChart!!.setDrawValueAboveBar(true)：设置柱形图数值是否显示在柱形上方，这里将数值显示在柱形上方。

mChart!!.description.isEnabled = false：设置是否显示图表的描述（Description），这里将描述禁用，即不显示描述信息。

mChart!!.setMaxVisibleValueCount(60)：设置图表最大可见数值的数量为60，当超过60个数值时，将不再绘制数值。

mChart!!.setPinchZoom(false)：设置是否启用捏合缩放（Pinch Zoom）功能，这里将捏合缩放功能禁用。

mChart!!.setDrawGridBackground(false)：设置是否绘制图表的网格背景，这里将网格背景绘制关闭。*/
        mChart!!.setOnChartValueSelectedListener(this)
        mChart!!.setDrawBarShadow(false)
        mChart!!.setDrawValueAboveBar(true)
        mChart!!.description.isEnabled = false
        mChart!!.setMaxVisibleValueCount(60)
        mChart!!.setPinchZoom(false)
        mChart!!.setDrawGridBackground(false)

        /*val xAxisFormatter: IAxisValueFormatter = XAxisValueFormatter()：创建了一个 X 轴的数值格式化器
        xAxisFormatter，并将其实例化为 XAxisValueFormatter 的一个实例，用于格式化 X 轴的数值显示。

val xAxis = mChart!!.xAxis：获取图表 mChart 的 X 轴对象 xAxis。

xAxis.position = XAxis.XAxisPosition.BOTTOM：设置 X 轴的位置为底部，即在图表底部显示 X 轴。

xAxis.setDrawAxisLine(true)：设置是否绘制 X 轴的轴线，这里将 X 轴的轴线绘制启用。

xAxis.granularity = 100f：设置 X 轴的粒度为100，用于控制 X 轴上数值的间隔。

xAxis.valueFormatter = xAxisFormatter：将之前创建的 xAxisFormatter（X 轴数值格式化器）设置为 X 轴的数值格式化器，
用于显示自定义的季节信息。*/
        //自定义坐标轴适配器，配置在X轴，xAxis.setValueFormatter(xAxisFormatter);
        val xAxisFormatter: IAxisValueFormatter = XAxisValueFormatter()
        val xAxis = mChart!!.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawAxisLine(true)
        xAxis.granularity = 1f
        xAxis.valueFormatter = xAxisFormatter


        //设置限制临界线
        val limitLine = LimitLine(3f, "临界点")
        limitLine.lineColor = Color.GREEN
        limitLine.lineWidth = 1f
        limitLine.textColor = Color.GREEN

        //获取到图形左边的Y轴
        //自定义坐标轴适配器，配置在Y轴。leftAxis.setValueFormatter(custom);
        /*val custom: IAxisValueFormatter = MyAxisValueFormatter()：创建了一个 Y 轴的自定义数值格式化器 custom，
        并实例化为 MyAxisValueFormatter 的一个实例，用于格式化 Y 轴的数值显示。

val leftAxis = mChart!!.axisLeft：获取图表 mChart 的左侧 Y 轴对象 leftAxis。

leftAxis.addLimitLine(limitLine)：向左侧 Y 轴添加一个限制线 limitLine，用于在图表上标记特定数值的限制线。

leftAxis.setLabelCount(8, false)：设置 Y 轴的标签数量为8，第二个参数表示是否根据间隔自动调整标签数量。

leftAxis.valueFormatter = custom：将之前创建的 custom（Y 轴的自定义数值格式化器）设置为 Y 轴的数值格式化器，
用于自定义显示 Y 轴的数值。

leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)：设置 Y 轴的标签位置在图表外侧。

leftAxis.spaceTop = 15f：设置 Y 轴顶部的间距为15f，即 Y 轴数值显示区域与图表顶部的距离为15个像素单位。

leftAxis.axisMinimum = 0f：设置 Y 轴的最小值为0，确保 Y 轴从0开始显示。*/
        val custom: IAxisValueFormatter = MyAxisValueFormatter()
        val leftAxis = mChart!!.axisLeft
        leftAxis.addLimitLine(limitLine)
        leftAxis.setLabelCount(8, false)
        leftAxis.valueFormatter = custom
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 1f
        leftAxis.axisMinimum = 0f

        //获取到图形右边的Y轴，并设置为不显示
        mChart!!.axisRight.isEnabled = false

        //图例设置
        val legend = mChart!!.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)
        legend.form = Legend.LegendForm.SQUARE
        legend.formSize = 9f
        legend.textSize = 11f
        legend.xEntrySpace = 4f

        //如果点击柱形图，会弹出pop提示框.XYMarkerView为自定义弹出框
        val mv = XYMarkerView(this, xAxisFormatter)
        mv.setChartView(mChart)
        mChart!!.marker = mv
        setBarChartData()
    }

    /**
     * 初始化水平柱形图图控件属性
     */
    private fun initHBarChart() {
        hBarChart!!.setOnChartValueSelectedListener(this)
        hBarChart!!.setDrawBarShadow(false)
        hBarChart!!.setDrawValueAboveBar(true)
        hBarChart!!.description.isEnabled = false
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        hBarChart!!.setMaxVisibleValueCount(60)

        // scaling can now only be done on x- and y-axis separately
        hBarChart!!.setPinchZoom(false)

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);
        hBarChart!!.setDrawGridBackground(false)

        //自定义坐标轴适配器，设置在X轴
        val formatter = DecimalFormatter()
        val xl = hBarChart!!.xAxis
        xl.position = XAxis.XAxisPosition.BOTTOM
        xl.labelRotationAngle = -45f
        xl.setDrawAxisLine(true)
        xl.setDrawGridLines(false)
        xl.granularity = 1f
        //        xl.setAxisMinimum(0);
        xl.valueFormatter = formatter

        //对Y轴进行设置
        val yl = hBarChart!!.axisLeft
        yl.setDrawAxisLine(true)
        yl.setDrawGridLines(true)
        yl.axisMinimum = 0f // this replaces setStartAtZero(true)
        //        yl.setInverted(true);
        hBarChart!!.axisRight.isEnabled = false

        //图例设置
        val l = hBarChart!!.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.formSize = 8f
        l.xEntrySpace = 4f
        setHBarChartData()
        hBarChart!!.setFitBars(true)
        hBarChart!!.animateY(2500)
    }

    /**
     * 初始化折线图控件属性
     */
    private fun initLineChart() {
        lineChart!!.setOnChartValueSelectedListener(this)
        lineChart!!.description.isEnabled = false
        lineChart!!.setBackgroundColor(Color.WHITE)

        //自定义适配器，适配于X轴
        val xAxisFormatter: IAxisValueFormatter = XAxisValueFormatter()
        val xAxis = lineChart!!.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.valueFormatter = xAxisFormatter

        //自定义适配器，适配于Y轴
        val custom: IAxisValueFormatter = MyAxisValueFormatter()
        val leftAxis = lineChart!!.axisLeft
        leftAxis.setLabelCount(8, false)
        leftAxis.valueFormatter = custom
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 15f
        leftAxis.axisMinimum = 0f
        lineChart!!.axisRight.isEnabled = false
        setLineChartData()
    }

    private fun getRandom(range: Float, startsfrom: Float): Float {
        return (Math.random() * range).toFloat() + startsfrom
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
    }

    override fun onNothingSelected() {}
    private fun setBarChartData() {
        val yVals1 = ArrayList<BarEntry>()
        //在这里设置自己的数据源,BarEntry 只接收float的参数，
        //图形横纵坐标默认为float形式，如果想展示文字形式，需要自定义适配器，
        yVals1.add(BarEntry(0f, 4f))
        yVals1.add(BarEntry(1f, 2f))
        yVals1.add(BarEntry(2f, 6f))
        yVals1.add(BarEntry(3f, 1f))
        val set1: BarDataSet
        if (mChart!!.data != null &&
            mChart!!.data.dataSetCount > 0
        ) {
            set1 = mChart!!.data.getDataSetByIndex(0) as BarDataSet
            set1.values = yVals1
            mChart!!.data.notifyDataChanged()
            mChart!!.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "The year 2017")
            set1.setDrawIcons(false)
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.barWidth = 0.9f
            mChart!!.data = data
        }
    }

    /**
     * 设置水平柱形图数据的方法
     */
    private fun setHBarChartData() {
        //填充数据，在这里换成自己的数据源
        val yVals1 = ArrayList<BarEntry>()
        yVals1.add(BarEntry(0f, 4f))
        yVals1.add(BarEntry(1f, 2f))
        yVals1.add(BarEntry(2f, 6f))
        yVals1.add(BarEntry(3f, 1f))
        val set1: BarDataSet
        if (hBarChart!!.data != null &&
            hBarChart!!.data.dataSetCount > 0
        ) {
            set1 = hBarChart!!.data.getDataSetByIndex(0) as BarDataSet
            set1.values = yVals1
            hBarChart!!.data.notifyDataChanged()
            hBarChart!!.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "DataSet 1")
            set1.setDrawIcons(false)
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.barWidth = 0.5f
            hBarChart!!.data = data
        }
    }

    /**
     * 设置折线图的数据
     */
    private fun setLineChartData() {
        //填充数据，在这里换成自己的数据源
        val valsComp1: MutableList<Entry> = ArrayList()
        val valsComp2: MutableList<Entry> = ArrayList()

        valsComp1.add(Entry(0f, 2f))
        valsComp1.add(Entry(1f, 4f))
        valsComp1.add(Entry(2f, 0f))
        valsComp1.add(Entry(3f, 2f))

        valsComp2.add(Entry(0f, 2f))
        valsComp2.add(Entry(1f, 0f))
        valsComp2.add(Entry(2f, 4f))
        valsComp2.add(Entry(3f, 2f))

        //这里，每重新new一个LineDataSet，相当于重新画一组折线
        //每一个LineDataSet相当于一组折线。比如:这里有两个LineDataSet：setComp1，setComp2。
        //则在图像上会有两条折线图，分别表示公司1 和 公司2 的情况.还可以设置更多
        val setComp1 = LineDataSet(valsComp1, "Company 1 ")
        setComp1.axisDependency = YAxis.AxisDependency.LEFT
        setComp1.color = resources.getColor(R.color.red)
        setComp1.setDrawCircles(false)
        setComp1.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        val setComp2 = LineDataSet(valsComp2, "Company 2 ")
        setComp2.axisDependency = YAxis.AxisDependency.LEFT
        setComp2.setDrawCircles(true)
        setComp2.color = resources.getColor(R.color.red)
        setComp2.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        val dataSets: MutableList<ILineDataSet> = ArrayList()
        dataSets.add(setComp1)
        dataSets.add(setComp2)
        val lineData = LineData(dataSets)
        lineChart!!.data = lineData
        lineChart!!.invalidate()
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent()
            intent.setClass(context, FifteenActivity::class.java)
            context.startActivity(intent)
        }
    }
}
