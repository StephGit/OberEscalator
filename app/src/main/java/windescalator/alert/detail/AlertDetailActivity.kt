package windescalator.alert.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import windescalator.data.entity.Alert
import windescalator.data.repo.AlertRepo
import windescalator.di.Injector
import javax.inject.Inject
import windescalator.R
import windescalator.alert.AlertService
import windescalator.alert.detail.chart.ChartData
import windescalator.alert.detail.chart.WindDirectionChart

class AlertDetailActivity :
        AppCompatActivity()
//        RoundSelectorButton.OnSliceClickListener
{
    val windDirectionData = ChartData()
    private lateinit var windDirectionChart: WindDirectionChart
    private lateinit var alert: Alert
//    private lateinit var sliceSelectorButton: RoundSelectorButton

    @Inject
    lateinit var alertService: AlertService

    @Inject
    lateinit var alertRepo: AlertRepo

    companion object {
        fun newIntent(ctx: Context) = Intent(ctx, AlertDetailActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_detail)
        title = getString(R.string.alert_detail_activity_title)

        Injector.appComponent.inject(this)

        val extras = intent.extras
        val alertId = extras?.get("ALERT_ID") as Long?
        alertId?.let {
            getAlertFromRepo(alertId)
        }

        initChartData()

//        sliceSelectorButton = findViewById(R.id.btn_alert_wind_direction)
//        sliceSelectorButton.setOnSliceClickListener(this)


    }

    @SuppressLint("ResourceType")
    private fun initChartData() {
        windDirectionChart = findViewById(R.id.btn_alert_wind_direction)
        val DIRECTIONS: Array<String> = arrayOf<String>("E", "SE", "S", "SW", "W", "NW", "N", "NE")
        val colorSlice = resources.getString(R.color.windEscalator_colorBrandLight)
        DIRECTIONS.forEach {
            windDirectionData.add(it, colorSlice)
        }
        windDirectionChart.setData(windDirectionData)
    }

    private fun getAlertFromRepo(alertId: Long) {
        alertRepo.getAlert(alertId)?.let {
            this.alert = it
        }
    }

//    override fun onSlickClick(slicePosition: Int) {
//        println("Slice Position: " + slicePosition)
//    }
}

