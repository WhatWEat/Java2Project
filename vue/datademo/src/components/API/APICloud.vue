<template>
  <el-container>
    <el-row style="display: flex; flex-direction: column; align-items: center;">
      <div id="APICloud" style="width: 80vw; height: 80vh"/>
    </el-row>
  </el-container>
</template>

<script>
import axios from "axios";
export default {
  name: "APICloud",
  data(){
    return{
      chart: null,
      graphData: {
        tooltip: {
          show: true,
          formatter: function(params) {
            return "<div><p style='font-weight: bold; font-size: 14px;'>次数</p><p>" + params.data.name + ": " + params.data.value + "</p></div>";
          },
          textStyle: {
            fontSize: 16
          }
        },
        series: [
          {
            type: "wordCloud",
            sizeRange: [12, 80],
            rotationRange: [-90, 90],
            rotationStep: 45,
            gridSize: 8,
            shape: "rectangle",
            width: "100%",
            height: "100%",
            drawOutOfBound: true,
            textStyle: {
              color: function () {
                return (
                    "rgb(" +
                    [
                      Math.round(Math.random() * 160),
                      Math.round(Math.random() * 160),
                      Math.round(Math.random() * 160),
                    ].join(",") +
                    ")"
                );
              },
            },
            data: [],
          },
        ],
      },

    }
  },
  methods: {
    getAPIData(){
      axios.get("/JavaAPI/Q1").then(res => {
        console.log(res.data);
        this.graphData.series[0].data = res.data;
        this.chart = this.$echarts.init(document.getElementById("APICloud"));
        this.chart.setOption(this.graphData);
      })
    }
  },
  mounted() {
    this.getAPIData();
  }
}
</script>

<style scoped>

</style>