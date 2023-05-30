<template>
  <div id="acceptedAnswer2" style="width: 40vw; height: 60vh"/>
</template>

<script>
import axios from "axios";

export default {
  name: "OverUpvote",
  data() {
    return {
      chart: null,
      graphData: {
        title: {
          text: '非采纳答案赞数高于采纳答案占比',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)',
        },
        series: [
          {
            name: '采纳占比',
            type: 'pie',
            radius: [20, 200],
            center: ['50%', '50%'],
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
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
              {value: 4, name: '未采纳赞数答案高于采纳赞数'},
              {value: 33, name: '未采纳赞数答案不大于采纳赞数'},
            ]
          }
        ]
      },
    }
  },
  methods: {
    getData(){
      axios.get('/AcceptedAnswers/Q3').then(res => {
        console.log(res.data);
        this.graphData.series[0].data[0].value = res.data[0];
        this.graphData.series[0].data[1].value = res.data[1] - res.data[0];
        this.drawChart();
      })
    },
    drawChart(){
      this.chart = this.$echarts.init(document.getElementById("acceptedAnswer2"));
      // 指定图表的配置项和数据
      let option = this.graphData;
      // 使用刚指定的配置项和数据显示图表。
      this.chart.setOption(option);
    }
  },
  mounted() {
    this.getData();
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