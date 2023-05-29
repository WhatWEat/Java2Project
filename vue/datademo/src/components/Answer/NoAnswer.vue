<template>
  <!--  使用没有答案的百分比-->
  <div id="noAnswer" style="width: 80vw; height: 80vh"/>
</template>

<script>

import axios from "axios";

export default {
  name: "NoAnswer",
  data() {
    return {
      chart: null,
      noAnswerData: null,
      timeAnswerData: null,
      graphData: {
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
          data: [
          ]
        },
        series: [
          {
            name: 'Problem Distribution',
            type: 'pie',
            selectedMode: 'single',
            radius: [0, '30%'],
            label: {
              position: 'inner',
              fontSize: 14
            },
            labelLine: {
              show: false
            },
            data: [
            ]
          },
          {
            name: 'Problem Distribution',
            type: 'pie',
            radius: ['45%', '60%'],
            labelLine: {
              length: 30
            },
            label: {
              formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
              backgroundColor: '#F6F8FC',
              borderColor: '#8C8D8E',
              borderWidth: 1,
              borderRadius: 4,
              rich: {
                a: {
                  color: '#6E7079',
                  lineHeight: 22,
                  align: 'center'
                },
                hr: {
                  borderColor: '#8C8D8E',
                  width: '100%',
                  borderWidth: 1,
                  height: 0
                },
                b: {
                  color: '#4C5058',
                  fontSize: 14,
                  fontWeight: 'bold',
                  lineHeight: 33
                },
                per: {
                  color: '#fff',
                  backgroundColor: '#4C5058',
                  padding: [3, 4],
                  borderRadius: 4
                }
              }
            },
            data: [
            ]
          }
        ]
      }
    };
  },
  methods: {
    getData() {
      axios.get('/AcceptedAnswers/Q2').then(res => {
        this.timeAnswerData = res.data;
        console.log(this.timeAnswerData);
        this.graphData.series[1].data = this.timeAnswerData.map(item => ({
            value: item.value,
            name: item.name,
        }));
        this.graphData.legend.data = this.timeAnswerData.map(item => item.name);
        this.drawChart();
      });
      axios.get('/NumberOfAnswers/Q1').then(res => {
        this.noAnswerData = res.data;
        console.log(this.noAnswerData);
        this.graphData.series[0].data = [{
          name: 'No Answer',
          value: this.noAnswerData[0]
        },{
          name: 'Have Answer',
          value: this.noAnswerData[1]
        }];
        this.drawChart();
      });
    },
    drawChart() {
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      this.chart = this.$echarts.init(document.getElementById("noAnswer"));
      // 指定图表的配置项和数据
      let option = this.graphData;
      // 使用刚指定的配置项和数据显示图表。
      this.chart.setOption(option);
    },
  },
  mounted() {
    this.getData();
    console.log(this.graphData);

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