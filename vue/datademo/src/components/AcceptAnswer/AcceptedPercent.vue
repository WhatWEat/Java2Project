<template>
  <div id="acceptedAnswer1" style="width: 40vw; height: 60vh"/>
</template>

<script>
import axios from "axios";

export default {
  name: "AcceptedPercent",
  data() {
    return {
      chart: null,
      graphData: {
        title: {
          text: '采纳占比',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)',
        },
        toolbox: {
          show: true,
          feature: {
            mark: {show: false},
            dataView: {show: false, readOnly: false},
            restore: {show: false},
            saveAsImage: {show: false}
          }
        },
        series: [
          {
            name: '采纳占比',
            type: 'pie',
            radius: [20, 200],
            center: ['50%', '50%'],
            roseType: 'radius',
            itemStyle: {
              borderRadius: 5
            },
            label: {
              show: false
            },
            emphasis: {
              label: {
                show: true
              }
            },
            data: [
              {value: 40, name: '采纳数'},
              {value: 33, name: '未采纳数'},
            ]
          }
        ]
      },
    }
  },
  methods: {
    getData(){
      axios.get('/AcceptedAnswers/Q1').then(res => {
        console.log(res.data);
        this.graphData.series[0].data[0].value = res.data[0];
        this.graphData.series[0].data[1].value = res.data[1] - res.data[0];
        this.drawChart();
      })
    },
    drawChart(){
      this.chart = this.$echarts.init(document.getElementById("acceptedAnswer1"));
      // 指定图表的配置项和数据
      let option = this.graphData;
      // 使用刚指定的配置项和数据显示图表。
      this.chart.setOption(option);
    }
  },
  mounted() {
    this.getData()
  },
  beforeDestroy() {
    if (this.chart != null) {
      this.chart.dispose();
    }
  }
}
</script>

<style scoped>

</style>